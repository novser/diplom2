import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static File logFile = new File("src\\main\\resources\\File.log");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static StringBuilder log = new StringBuilder();
    private static final String inputMassagePattern = "От пользователя %s пришло сообщение: \"%s\"";
    private static final String outputMassagePattern = "От пользователя %s отправлено сообщение: \"%s\"";

    private static void writeLog(String name, String logText) {
        log.append(String.format("%s %s: %s \n", currentTime(), name, logText));
        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(log.toString());
            writer.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String currentTime() {
        return formatter.format(new Date());
    }

    public static void writeStart(String name) {
        writeLog(name, "запуск");
    }

    public static void writeFinish(String name) {
        writeLog(name, "отключение");
    }

    public static void writeInputMassage(String name, Massage massage) {
        writeLog(name, String.format(inputMassagePattern, massage.getSender(), massage.getText()));
    }

    public static void writeOutputMassage(String name, Massage massage, boolean itIsServer) {
        writeLog(name, String.format(outputMassagePattern, itIsServer ? massage.getSender() : "", massage.getText()));
    }

    public static void writeError(String name, String error) {
        writeLog(name, error);
    }
}
