package ohtu;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static ohtu.StatFetcher.getStatsAsJsonObject;
import static ohtu.StatFetcher.getStatsAsObject;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014686481";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String submissionUrl = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String totalStatUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";

        Submission[] subs = (Submission[]) getStatsAsObject(submissionUrl, Submission[].class);
        Course course = (Course) getStatsAsObject(courseUrl, Course.class);
        JsonObject totalStats = getStatsAsJsonObject(totalStatUrl);

        System.out.print("Opiskelijanumero: ");
        System.out.println(studentNr);

        System.out.print("Kurssi: ");
        System.out.println(course.getName() + ", " + course.getTerm());

        int totalExercisesForStudent = 0;
        int totalHours = 0;
        System.out.println();
        for (int i = 1; i <= course.getWeek(); i++) {
            System.out.println("viikko " + i);
            Optional<Submission> sub = findSubmissionByWeek(subs, i);
            if (sub.isPresent()) {
                Submission submission = sub.get();
                totalExercisesForStudent += submission.getExercises().length;
                totalHours += submission.getHours();
                System.out.print("    ");
                System.out.println("" +
                        "tehtyjä tehtäviä yhteensä: " + submission.getExercises().length +
                        "/" + course.getExercises()[i-1]  +
                        ", aikaa kului " + submission.getHours() + " tuntia, " +
                        "tehdyt tehtävät: " + exercisesAsString(submission));
            } else {
                System.out.println("Ei palautusta");
            }
        }

        System.out.println();

        System.out.println("Yhteensä: " + totalExercisesForStudent +
                            " tehtävää ja " + totalHours +
                            " tuntia");

        int totalSubmissions = 0;
        int totalExercisesForCourse = 0;
        for(Map.Entry<String, JsonElement> entry: totalStats.entrySet()) {
            JsonObject weekData = entry.getValue().getAsJsonObject();
            totalSubmissions += weekData.get("students").getAsInt();
            totalExercisesForCourse += weekData.get("exercise_total").getAsInt();
        }

        System.out.println("Kurssilla yhteensä " + totalSubmissions + " palautusta" +
                            ", palautettuja tehtäviä " + totalExercisesForCourse + " kpl.");

    }

    private static Optional<Submission> findSubmissionByWeek(Submission[] submissions, int week) {
        return Arrays.stream(submissions).filter(s -> s.getWeek() == week).findFirst();
    }

    private static String exercisesAsString(Submission submission) {
        return Arrays.stream(submission.getExercises())
                .mapToObj(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));
    }
}
