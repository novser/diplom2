import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerTest {

    @Test
    public void testRun() {
        Server server = new Server();
        server.start();
        Assertions.assertTrue(server.isAlive());
    }
}
