package edu.hw7.task3;

import edu.hw7.task3.entity.Person;
import edu.hw7.task3.repository.PersonDatabase;
import edu.hw7.task3.repository.impl.ThreadedPersonDatabase;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonDatabaseTest {
    private static final Logger LOGGER = LogManager.getLogger();

    private final List<Person> persons = List.of(
        new Person(1, "Artem", "221B", "8-111-222-33-44"),
        new Person(3, "Andrew", "Russia", "8-222-222-33-00"),
        new Person(5, "John", "103/1", "8-777-888-99-11")
    );

    private final Person newPerson = new Person(
        5, "Anton", "11/1", "4-444-444-11-11"
    );

    @Test
    void testDatabase() {
        PersonDatabase personDatabase = new ThreadedPersonDatabase();
        persons.forEach(personDatabase::add);

        Thread thread1 = new Thread(() -> {
            var person = personDatabase.findByName("Artem");
            assertNotNull(person);
        });

        Thread thread2 = new Thread(() -> {
            personDatabase.delete(1);
        });

        Thread thread3 = new Thread(() -> {
            personDatabase.add(
                newPerson
            );
        });

        Thread thread4 = new Thread(() -> {
            var person = personDatabase.findByName("Anton");
            assertEquals(
                newPerson,
                person.get(0)
            );
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            LOGGER.error(e.getStackTrace());
        }

    }
}
