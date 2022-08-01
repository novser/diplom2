import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

public class Server extends Thread {

    private final int port;
    private final String ip;
    private final String name = "SERVER";
    private volatile ConcurrentMap<String, StringBuilder> massages = new ConcurrentHashMap();

    public Server() {
        Map<String, String> settings = Main.getSettings(name);
        this.port = Integer.parseInt(settings.get("port"));
        this.ip = settings.get("ip");
    }

    @Override
    public void run() {
        Log.writeStart(name);

        try (ServerSocket servSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = servSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line = in.readLine();
                    String[] parts = line.split(":");
                    String userName = parts[0];

                    Log.writeInputMassage(name, parts);
                    if (line.equals("exit")) {
                        Log.writeFinish(userName);
                        break;
                    }

                    StringBuilder value = massages.get(userName);
                    if (value == null) {
                        massages.put(userName, new StringBuilder());
                        value = massages.get(userName);
                    }
                    out.println(value);
                    Log.writeOutputMassage(name, parts);
                    massages.forEach((k, v) -> {
                        if (!k.equals(userName)) {
                            v.append(line + "\n");
                        }
                    });
                }
            }
        } catch (Exception e) {
            Log.writeError(name, e.getMessage());
        } finally {
            Log.writeFinish(name);
        }
    }
}