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

    private final File file;

    public DiskMap(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public int size() {
        return readFromFile().size();
    }

    @Override
    public boolean isEmpty() {
        return readFromFile().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return readFromFile().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return readFromFile().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return readFromFile().get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Map<String, String> map = readFromFile();
        String oldValue = map.put(key, value);
        writeToFile(map);
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> map = readFromFile();
        String removedValue = map.remove(key);
        writeToFile(map);
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        Map<String, String> map = readFromFile();
        map.putAll(m);
        writeToFile(map);
    }

    @Override
    public void clear() {
        writeToFile(new HashMap<>());
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return readFromFile().keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return readFromFile().values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return readFromFile().entrySet();
    }

    private Map<String, String> readFromFile() {
        Map<String, String> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

        return map;
    }

    private void writeToFile(Map<String, String> map) {
        try {
            List<String> lines = map.entrySet().stream()
                .map(
                    entry -> entry.getKey() + ":" + entry.getValue()
                )
                .toList();

            Files.write(file.toPath(), lines, Charset.defaultCharset());

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

}
