import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server extends Thread {

    private final int port;
    private final String ip;
    private int usersCount;
    private ExecutorService threadPool = Executors.newFixedThreadPool(usersCount);
    private final String name = "SERVER";

    public Server(int usersCount) {
        Map<String, String> settings = Main.getSettings(name);
        this.port = Integer.parseInt(settings.get("port"));
        this.ip = settings.get("ip");
        this.usersCount = usersCount;
    }

    @Override
    public void run() {
        Log.writeStart(name);

        try (ServerSocket servSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = servSocket.accept()) {
                    Runnable connect = new Connect(socket);
                    Future future = threadPool.submit(connect);
                    future.get();
                }
            }
        } catch (Exception e) {
            Log.writeError(name, e.getMessage());
        }
    }

    private class Connect implements Runnable {
        volatile Socket socket;

        public Connect(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    Massage massage = JsonHelper.getMassageFromJson(line);
                    Log.writeInputMassage(name, massage);
                    out.println(JsonHelper.getJsonTextFromMassage(massage));
                    Log.writeOutputMassage(name, massage);
                    if (line.equals("exit")) {
                        break;
                    }
                }
            } catch (Exception e) {
                Log.writeError(name, e.getMessage());
            }
        }
    }
}