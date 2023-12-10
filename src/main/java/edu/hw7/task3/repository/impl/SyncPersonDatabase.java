package edu.hw7.task3.repository.impl;

import edu.hw7.task3.entity.Person;
import edu.hw7.task3.repository.PersonDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> personMap = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personMap.put(person.id(), person);

        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized void delete(int id) {
        if (!personMap.containsKey(id)) {
            return;
        }

        Person person = personMap.remove(id);

        removeFromIndex(nameIndex, person.name(), person);
        removeFromIndex(addressIndex, person.address(), person);
        removeFromIndex(phoneIndex, person.phoneNumber(), person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return getIndexValue(nameIndex, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return getIndexValue(addressIndex, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return getIndexValue(phoneIndex, phone);
    }

    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> persons = index.getOrDefault(key, new ArrayList<>());
        persons.add(person);
        index.put(key, persons);
    }

    private void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> persons = index.get(key);
        if (persons != null) {
            persons.remove(person);
            if (persons.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private List<Person> getIndexValue(Map<String, List<Person>> index, String key) {
        List<Person> persons = index.get(key);
        return persons != null ? new ArrayList<>(persons) : null;
    }
}
