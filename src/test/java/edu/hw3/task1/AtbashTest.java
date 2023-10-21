package edu.hw3.task1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashTest {

    @ParameterizedTest
    @DisplayName("test Atbash cipher")
    @MethodSource("provideValues")
    public void testAtbashCipher(String input, String expectedResult) {
        String result = AtbashCipher.encryptString(input);
        assertThat(result)
            .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of("abcd", "zyxw"),
            Arguments.of("ABCD", "ZYXW"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }

}
