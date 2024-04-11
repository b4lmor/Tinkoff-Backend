package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBuddyFibTest {

    @Test
    void testFib()
        throws NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {

        Class<?> createdClass = createClass();
        long result = (long) createdClass.getDeclaredMethod("fib", int.class)
            .invoke(createdClass.getDeclaredConstructor().newInstance(), 10);
        assertEquals(55, result);
    }

    private Class<?> createClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCalculator")
            .defineMethod(
                "fib",
                long.class,
                Modifier.PUBLIC
            ).withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();

                        mv.visitCode();
                        // x < 2
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        // F(x - 1) + F(x - 2);
                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(
                            Opcodes.INVOKEVIRTUAL,
                            "FibonacciCalculator",
                            "fib",
                            "(I)J"
                        );
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(
                            Opcodes.INVOKEVIRTUAL,
                            "FibonacciCalculator",
                            "fib", "(I)J"
                        );
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();

                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }
}
