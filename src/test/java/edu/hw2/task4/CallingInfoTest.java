package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {
    @Test
    @DisplayName("Who called the function?")
    void testTaskExample() {
        var callingInfo = CallingInfo.callingInfo();

        assertThat(callingInfo.className())
            .isEqualTo("edu.hw2.task4.CallingInfoTest");

        assertThat(callingInfo.methodName())
            .isEqualTo("testTaskExample");
    }

}
