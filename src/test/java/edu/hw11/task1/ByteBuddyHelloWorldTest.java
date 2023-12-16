package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyHelloWorldTest {

    private static final String STRING = "Hello, ByteBuddy!";

    @Test
    void testByteBodyClassCreation() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(STRING))
            .make()
            .load(ByteBuddyHelloWorldTest.class.getClassLoader())
            .getLoaded();

        Object instance = dynamicType.newInstance();

        var result = instance.toString();

        assertEquals(
            STRING,
            result
        );
    }

}
