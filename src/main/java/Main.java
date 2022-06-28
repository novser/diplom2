import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final String settingsPath = "src\\main\\resources\\settings.txt";

    public static void main(String[] args) {
        new Server().start();
    }

    public static Map<String, String> getSettings(String name) {
        StringBuilder builder = new StringBuilder();
        try (FileInputStream fin = new FileInputStream(settingsPath)) {
            int i;
            while ((i = fin.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException ex) {
            Log.writeError(name, ex.getMessage());
        }

        Map<String, String> settings = Arrays.stream(builder.toString().split(";"))
                .map(element -> element.split(":"))
                .collect(Collectors.toMap(element -> clearWord(element[0]), element -> clearWord(element[1])));

        return settings;
    }

    private static String clearWord(String word) {
        return word.replaceAll("(?U)[\\pP\\s]", "");
    }
}
