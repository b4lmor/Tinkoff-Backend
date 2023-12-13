package edu.hw10.task2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ObjectDiskMap implements Map<String, Object> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEPARATOR = " -> ";
    private final File file;
    private Map<String, Object> cacheMap = null;

    public ObjectDiskMap(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int size() {
        return getCacheMap().size();
    }

    @Override
    public boolean isEmpty() {
        return getCacheMap().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return getCacheMap().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return getCacheMap().containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return getCacheMap().get(key);
    }

    @Nullable
    @Override
    public Object put(String key, Object value) {
        return getCacheMap().put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return getCacheMap().remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ?> m) {
        getCacheMap().putAll(m);
    }

    @Override
    public void clear() {
        writeToFile(new HashMap<>());
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return getCacheMap().keySet();
    }

    @NotNull
    @Override
    public Collection<Object> values() {
        return getCacheMap().values();
    }

    @NotNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return getCacheMap().entrySet();
    }

    public void save() {
        writeToFile(getCacheMap());
    }

    private void writeToFile(Map<String, Object> map) {
        try {
            List<String> lines = map.entrySet().stream()
                .map(
                    entry -> entry.getKey() + SEPARATOR + entry.getValue().toString()
                )
                .toList();

            Files.write(file.toPath(), lines, Charset.defaultCharset());

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Map<String, Object> getCacheMap() {
        if (cacheMap == null) {
            cacheMap = new HashMap<>();
        }
        return cacheMap;
    }
}
