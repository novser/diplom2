import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonHelperTest {

    @Test
    public void testGetMassageFromJson() {
        Massage expected = new Massage("TestUser", "TestText");

        GsonBuilder jsonBuilder = new GsonBuilder();
        Gson gson = jsonBuilder.create();
        Massage result = JsonHelper.getMassageFromJson(gson.toJson(expected));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testGetJsonTextFromMassage() {
        Massage massage = new Massage("TestUser", "TestText");
        GsonBuilder jsonBuilder = new GsonBuilder();
        Gson gson = jsonBuilder.create();
        String expected = gson.toJson(massage);

        String result = JsonHelper.getJsonTextFromMassage(massage);

        Assertions.assertEquals(expected, result);
    }
}
