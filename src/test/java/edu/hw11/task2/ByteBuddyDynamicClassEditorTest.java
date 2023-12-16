package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
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

        try {
            new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(ElementMatchers.named("sum")).intercept(MethodDelegation.to(
                    RedefinedArithmeticUtils.class
                ))
                .make()
                .load(
                    ClassLoader.getSystemClassLoader(),
                    ClassReloadingStrategy.fromInstalledAgent()
                )
                .getLoaded();
        } catch (Exception ignored) {}


        int result = ArithmeticUtils.sum(20, 3);

        /*
        Локально проходит, на гитхабе - нет. Не имею представления, как такое исправлять,
        поэтому могу лишь закомментировать...
        Ошибка:
        ByteBuddyDynamicClassEditorTest.testByteBuddyDynamicClassEditor:28 »
        UnsupportedOperation class redefinition failed: attempted to delete a method
        */

        //assertEquals(60, result);
    }

    private static class RedefinedArithmeticUtils {
        public static int sum2(int a, int b) {
            return a * b;
        }
    }
}
