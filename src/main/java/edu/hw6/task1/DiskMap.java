package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class DiskMap implements Map<String, String> {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SEPARATOR = ":";

    private final File file;
    private Map<String, String> cacheMap = null;

    public DiskMap(String filePath) {
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
    public String get(Object key) {
        return getCacheMap().get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return getCacheMap().put(key, value);
    }

    @Override
    public String remove(Object key) {
        return getCacheMap().remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
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
    public Collection<String> values() {
        return getCacheMap().values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return getCacheMap().entrySet();
    }

    public void save() {
        writeToFile(getCacheMap());
    }

    private Map<String, String> readFromFile() {
        Map<String, String> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return map;
    }

    private void writeToFile(Map<String, String> map) {
        try {
            List<String> lines = map.entrySet().stream()
                .map(
                    entry -> entry.getKey() + SEPARATOR + entry.getValue()
                )
                .toList();

            Files.write(file.toPath(), lines, Charset.defaultCharset());

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Map<String, String> getCacheMap() {
        if (cacheMap == null) {
            cacheMap = readFromFile();
        }
        return  cacheMap;
    }
}
