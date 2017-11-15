package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.print("Opiskelijanumero:");
        System.out.println(studentNr);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        int totalExercises = 0;
        int totalHours = 0;
        System.out.println();
        for (Submission submission : subs) {
            totalExercises += submission.getExercises().length;
            totalHours += submission.getHours();
            System.out.println("  " + submission);
        }
        System.out.println();

        System.out.println("Yhteensä: " + totalExercises +
                            " tehtävää ja " + totalHours +
                            " tuntia");


    }
}
