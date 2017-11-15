package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014686481";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String submissionUrl = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";

        String submissionBodyText = Request.Get(submissionUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(submissionBodyText, Submission[].class);
        Course course = mapper.fromJson(courseBodyText, Course.class);

        System.out.print("Opiskelijanumero: ");
        System.out.println(studentNr);

        System.out.print("Kurssi: ");
        System.out.println(course.getName() + ", " + course.getTerm());

        int totalExercises = 0;
        int totalHours = 0;
        System.out.println();
        for (int i = 1; i <= course.getWeek(); i++) {
            System.out.println("viikko " + i);
            Optional<Submission> sub = findSubmissionByWeek(subs, i);
            if (sub.isPresent()) {
                Submission submission = sub.get();
                totalExercises += submission.getExercises().length;
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

        System.out.println("Yhteensä: " + totalExercises +
                            " tehtävää ja " + totalHours +
                            " tuntia");


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
