package edu.hw11.task2;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyDynamicClassEditorTest {

    @BeforeAll
    static void install() {
        ByteBuddyAgent.install();
    }

    @Test
    public void testByteBuddyDynamicClassEditor() {

        new AgentBuilder.Default()
            .type(ElementMatchers.nameContains("ArithmeticUtils"))
            .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.method(ElementMatchers.named("sum"))
                    .intercept(MethodDelegation.to(new Object() {
                        public int sum(int a, int b) {
                            return a * b;
                        }
                    })))
            .installOnByteBuddyAgent();


        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        int result = arithmeticUtils.sum(20, 3);

        assertEquals(60, result);
    }


}
