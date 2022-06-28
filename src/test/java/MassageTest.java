import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Stream;

public class MassageTest {

    @ParameterizedTest
    @MethodSource("setParams")
    public void testGetSender(String user) {
        String expected = user;
        Massage result = new Massage(expected);
        Assertions.assertEquals(expected, result.getSender());
    }

    @ParameterizedTest
    @MethodSource("setParams")
    public void testGetText(String user, String text) {
        String expected = text;
        Massage result = new Massage(user, expected);
        Assertions.assertEquals(expected, result.getText());
    }

    @ParameterizedTest
    @MethodSource("setParams")
    public void testSetText(String user, String text) {
        String expected = text;
        Massage result = new Massage(user);
        result.setText(expected);
        Assertions.assertEquals(expected, result.getText());
    }

    static Stream<Arguments> setParams() {
        return Stream.of(
                Arguments.of("TestUser"),
                Arguments.of("TestText")
        );
    }
}
