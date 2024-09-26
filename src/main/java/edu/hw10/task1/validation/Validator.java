package edu.hw10.task1.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Validator {

    private Validator() {
    }

    private static final Map<Class<?>, BiFunction<Object, Field, Boolean>> ONE_ARG_VALIDATORS
        = new HashMap<>();

    private static final Class<? extends Annotation>[] AVAILABLE_ONE_ARG_VALIDATORS = new Class[] {
        Min.class,
        Max.class,
        MyNotNull.class
    };

    static {
        ONE_ARG_VALIDATORS.put(
            Min.class,
            (object, field) -> {
                try {
                    field.setAccessible(true);
                    int value = (int) field.get(object);
                    int minValue = field.getAnnotation(Min.class).value();

                    if (value < minValue) {
                        throw new IllegalArgumentException(
                            "Значение поля " + field.getName() + " должно быть не меньше " + minValue
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        );

        ONE_ARG_VALIDATORS.put(
            Max.class,
            (object, field) -> {
                try {
                    field.setAccessible(true);
                    int value = (int) field.get(object);
                    int maxValue = field.getAnnotation(Min.class).value();

                    if (value > maxValue) {
                        throw new IllegalArgumentException(
                            "Значение " + field.getName() + " должно быть не больше " + maxValue
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        );

        ONE_ARG_VALIDATORS.put(
            MyNotNull.class,
            (object, field) -> {
                try {
                    field.setAccessible(true);
                    if (field.get(object) == null) {
                        throw new IllegalArgumentException(
                            "Поле " + field.getName() + " не может быть null"
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        );
    }

    public static void validate(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();

        Arrays.stream(fields).forEach(
            field -> Arrays.stream(AVAILABLE_ONE_ARG_VALIDATORS)
                .filter(field::isAnnotationPresent)
                .forEach(
                    validator -> ONE_ARG_VALIDATORS.get(validator).apply(object, field)
                )
        );
    }

}
