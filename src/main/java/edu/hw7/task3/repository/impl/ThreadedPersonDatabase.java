package edu.hw7.task3.repository.impl;

import edu.hw7.task3.entity.Person;
import edu.hw7.task3.repository.PersonDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ThreadedPersonDatabase implements PersonDatabase {

    private final Map<Integer, Person> idToPerson = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberToPerson = new HashMap<>();
    private final Map<String, List<Person>> nameToPerson = new HashMap<>();
    private final Map<String, List<Person>> addressToPerson = new HashMap<>();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            idToPerson.put(person.id(), person);
            addToMap(person, person.name(), nameToPerson);
            addToMap(person, person.address(), addressToPerson);
            addToMap(person, person.phoneNumber(), phoneNumberToPerson);

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        Person personToDelete;

        lock.readLock().lock();

        try {
            personToDelete = Objects.requireNonNull(
                idToPerson.entrySet().stream()
                .filter(
                    p -> p.getValue().id() == id
                )
                .findFirst()
                .orElse(null)
            ).getValue();

        } finally {
            lock.readLock().unlock();
        }

        if (personToDelete == null) {
            return;
        }

        lock.writeLock().lock();

        try {
            idToPerson.remove(personToDelete.id());
            nameToPerson.remove(personToDelete.name());
            addressToPerson.remove(personToDelete.address());
            phoneNumberToPerson.remove(personToDelete.phoneNumber());

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        lock.readLock().lock();

        try {
            return nameToPerson.getOrDefault(name, null);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        lock.readLock().lock();

        try {
            return addressToPerson.getOrDefault(address, null);

        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByPhone(String phoneNumber) {
        lock.readLock().lock();

        try {
            return phoneNumberToPerson.getOrDefault(phoneNumber, null);

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
