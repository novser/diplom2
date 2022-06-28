import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {

    private static final GsonBuilder jsonBuilder = new GsonBuilder();

    public static Massage getMassageFromJson(String jsonText) {
        Gson gson = jsonBuilder.create();
        return gson.fromJson(jsonText, Massage.class);
    }

    public static String getJsonTextFromMassage(Massage massage) {
        Gson gson = jsonBuilder.create();
        return gson.toJson(massage);
    }
}
