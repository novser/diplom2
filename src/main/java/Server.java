import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Server extends Thread {

    private final int port;
    private final String ip;
    private int buffer;
    private final String name = "SERVER";

    public Server() {
        Map<String, String> settings = Main.getSettings(name);
        this.port = Integer.parseInt(settings.get("port"));
        this.ip = settings.get("ip");
        this.buffer = Integer.parseInt(settings.get("buffer"));
    }

    @Override
    public void run() {
        Log.writeStart(name);

        ServerSocketChannel serverChannel = null;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            Log.writeError(name, e.getMessage());
        }

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(buffer);
                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) break;
                    final String text = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                    inputBuffer.clear();
                    Massage massage = JsonHelper.getMassageFromJson(text);
                    Log.writeInputMassage(name, massage);
                    socketChannel.write(ByteBuffer.wrap((JsonHelper.getJsonTextFromMassage(massage)).getBytes(StandardCharsets.UTF_8)));
                    Log.writeOutputMassage(name, massage);
                }
            } catch (IOException e) {
                Log.writeError(name, e.getMessage());
            }
        }

    }
}

