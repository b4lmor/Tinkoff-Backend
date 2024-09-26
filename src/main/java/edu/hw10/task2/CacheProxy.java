package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CacheProxy implements InvocationHandler {
    private static final String DATA_FILE_PATH = "src/main/resources/hw10/mapdata.dat";
    private static final String ARG_SEPARATOR = "_";
    private final Object target;
    private final ObjectDiskMap cache;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new ObjectDiskMap(DATA_FILE_PATH);
    }

    public static Object create(Object target, Class<?> targetInterface) {
        return Proxy.newProxyInstance(
            targetInterface.getClassLoader(),
            new Class<?>[]{targetInterface},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String cacheKey = generateCacheKey(method, args);

        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        }

        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        Object result = method.invoke(target, args);
        cache.put(cacheKey, result);

        if (method.getAnnotation(Cache.class).persist()) {
            cache.save();
        }

        return result;
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(method.getName());

        for (Object arg : args) {
            keyBuilder
                .append(ARG_SEPARATOR)
                .append(arg.toString());
        }

        return keyBuilder.toString();
    }
}
