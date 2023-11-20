package edu.hw7.task3.repository;

import edu.hw7.task3.entity.Person;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    @Nullable List<Person> findByName(String name);

    @Nullable List<Person> findByAddress(String address);

    @Nullable List<Person> findByPhone(String phone);
}
