import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class MainTest {

    @Test
    public void testIP() {
        Map<String, String> settings = Main.getSettings("Test");
        String result = settings.get("ip");
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(String.class, result);
    }

    @Test
    public void testPort() {
        Map<String, String> settings = Main.getSettings("Test");
        String result = settings.get("port");
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(Integer.class, Integer.parseInt(result));
    }

}
