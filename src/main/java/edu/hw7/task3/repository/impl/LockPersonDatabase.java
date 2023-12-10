package edu.hw7.task3.repository.impl;

import edu.hw7.task3.entity.Person;
import edu.hw7.task3.repository.PersonDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class LockPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> personMap = new HashMap<>();
    private final Map<String, List<Person>> nameIndex = new HashMap<>();
    private final Map<String, List<Person>> addressIndex = new HashMap<>();
    private final Map<String, List<Person>> phoneIndex = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            personMap.put(person.id(), person);
            addToMap(person, person.name(), nameIndex);
            addToMap(person, person.address(), addressIndex);
            addToMap(person, person.phoneNumber(), phoneIndex);

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        Person personToDelete;

        lock.readLock().lock();

        try {
            var entry = personMap.entrySet().stream()
                .filter(
                    p -> p.getValue().id() == id
                )
                .findFirst()
                .orElse(null);
            if (entry == null) {
                personToDelete = null;
            } else {
                personToDelete = entry.getValue();
            }

        } finally {
            lock.readLock().unlock();
        }

        if (personToDelete == null) {
            return;
        }

        lock.writeLock().lock();

        try {
            personMap.remove(personToDelete.id());
            nameIndex.get(personToDelete.name())
                .remove(personToDelete);
            addressIndex.get(personToDelete.address())
                .remove(personToDelete);
            phoneIndex.get(personToDelete.phoneNumber())
                .remove(personToDelete);

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        lock.readLock().lock();

        try {
            return nameIndex.getOrDefault(name, null);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        lock.readLock().lock();

        try {
            return addressIndex.getOrDefault(address, null);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByPhone(String phoneNumber) {
        lock.readLock().lock();

        try {
            return phoneIndex.getOrDefault(phoneNumber, null);

        } finally {
            lock.readLock().unlock();
        }
    }

    private void addToMap(Person person, String key, Map<String, List<Person>> map) {
        if (map.containsKey(key)) {
            map.get(key).add(person);

        } else {
            List<Person> persons = new ArrayList<>();
            persons.add(person);
            map.put(key, persons);
        }
    }
}
