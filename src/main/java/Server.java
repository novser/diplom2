import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server extends Thread {

    private final int port;
    private final String name = "SERVER";

    public Server() {
        Map<String, String> settings = Main.getSettings(name);
        this.port = Integer.parseInt(settings.get("port"));
    }

    @Override
    public void run() {
        Log.writeStart(name);
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
            ) {
                Massage massage = JsonHelper.getMassageFromJson(in.readLine());
                Log.writeInputMassage(name, massage);
                out.println(JsonHelper.getJsonTextFromMassage(massage));
                Log.writeOutputMassage(name, massage, true);
            } catch (Exception e) {
                Log.writeError(name, e.getMessage());
            }
        }
    }
}

