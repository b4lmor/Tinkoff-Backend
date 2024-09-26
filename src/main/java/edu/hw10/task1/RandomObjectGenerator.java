package edu.hw10.task1;

import edu.hw10.task1.validation.Max;
import edu.hw10.task1.validation.Min;
import edu.hw10.task1.validation.MyNotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class RandomObjectGenerator {
    private final Random random;

    public RandomObjectGenerator() {
        random = new Random();
    }

    public Object nextObject(Class<?> classObj)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {

        var constructors = classObj.getConstructors();
        var constructor = constructors[random.nextInt(0, constructors.length)];

        var parameterTypes = constructor.getParameterTypes();
        var arguments = new Object[parameterTypes.length];
        var annotations = constructor.getParameterAnnotations();

        for (int i = 0; i < parameterTypes.length; i++) {
            arguments[i] = getRandomValue(parameterTypes[i], List.of(annotations[i]));
        }

        return classObj.cast(constructor.newInstance(arguments));
    }

    public Object nextObject(Class<?> classObj, @NotNull String factoryMethodName)
        throws InvocationTargetException, IllegalAccessException {

        Method factoryMethod = Arrays.stream(classObj.getDeclaredMethods())
            .filter(
                method -> method.getName().equals(factoryMethodName)
            )
            .findFirst()
            .orElseThrow(
                () -> new RuntimeException("there is no such factory method")
            );

        var parameterTypes = factoryMethod.getParameterTypes();
        var annotations = factoryMethod.getParameterAnnotations();
        var arguments = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            arguments[i] = getRandomValue(parameterTypes[i], List.of(annotations[i]));
        }

        return classObj.cast(factoryMethod.invoke(null, arguments));
    }

    @SuppressWarnings("ReturnCount")
    private Object getRandomValue(Class<?> type, List<Annotation> annotations) {

        var minAnnotation
            = ((Min) annotations.stream()
            .filter(annotation -> annotation instanceof Min)
            .findFirst().orElse(null));
        var min = minAnnotation == null ? Integer.MIN_VALUE : minAnnotation.value();

        var maxAnnotation
            = ((Max) annotations.stream()
            .filter(annotation -> annotation instanceof Max)
            .findFirst().orElse(null));
        var max = maxAnnotation == null ? Integer.MAX_VALUE : maxAnnotation.value();

        var notNullRequiredAnnotation
            = ((MyNotNull) annotations.stream()
            .filter(annotation -> annotation instanceof MyNotNull)
            .findFirst().orElse(null));
        var notNullRequired = notNullRequiredAnnotation != null;

        if (min > max) {
            throw new IllegalArgumentException("Bad limits");
        }

        if (type == String.class) {
            return RandomStringGenerator.generateRandomString(notNullRequired);

        } else if (type == int.class || type == Integer.class) {
            return random.nextInt(min + 1, max);

        } else if (type == double.class || type == Double.class) {
            return random.nextDouble(min + 1, max);

        } else if (type == boolean.class || type == Boolean.class) {
            return random.nextBoolean();

        } else {
            return null;
        }
    }

    public static class RandomStringGenerator {
        private static final String CHARACTERS
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final int STRING_LENGTH = 10;
        private static final int NULL_DENSITY = 8;

        public static String generateRandomString(boolean notNullRequired) {
            Random random = new Random();

            if (!notNullRequired && random.nextInt() % NULL_DENSITY == 0) {
                return null;
            }

            StringBuilder sb = new StringBuilder(STRING_LENGTH);

            for (int i = 0; i < STRING_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(index);
                sb.append(randomChar);
            }

            return sb.toString();
        }

    }
}
