import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ServerTest {
    @Test
    public void testRun() throws IOException {
        Server server = new Server();
        server.start();
        Assertions.assertTrue(server.isAlive());
    }
}
