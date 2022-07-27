import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ServerTest {
    private static int usersCount = 4;
    @Test
    public void testRun() throws IOException {
        Server server = new Server(usersCount);
        server.start();
        Assertions.assertTrue(server.isAlive());
    }
}
