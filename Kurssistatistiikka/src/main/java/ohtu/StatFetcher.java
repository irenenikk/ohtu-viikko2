package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * Created by nikkaire on 16.11.2017.
 */
public class StatFetcher {

    private static String getBodyText(String url) throws IOException{
        return Request.Get(url).execute().returnContent().asString();
    }

    public static Object getStatsAsObject(String url, Class objectClass) {
        String bodyText;
        try {
            bodyText = getBodyText(url);
        } catch (IOException e) {
            return null;
        }
        Gson mapper = new Gson();
        return mapper.fromJson(bodyText, objectClass);
    }

    public static JsonObject getStatsAsJsonObject(String url) {
        String bodyText;
        try {
            bodyText = getBodyText(url);
        } catch (IOException e) {
            return null;
        }
        JsonParser parser = new JsonParser();
        return parser.parse(bodyText).getAsJsonObject();
    }
}
