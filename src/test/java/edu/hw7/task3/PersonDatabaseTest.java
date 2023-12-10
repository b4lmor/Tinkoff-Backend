package edu.hw7.task3;

import edu.hw7.task3.entity.Person;
import edu.hw7.task3.repository.PersonDatabase;
import edu.hw7.task3.repository.impl.LockPersonDatabase;
import edu.hw7.task3.repository.impl.SyncPersonDatabase;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonDatabaseTest {

    private final Person newPerson = new Person(
        5, "Anton", "11/1", "4-444-444-11-11"
    );

    public static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(
                new SyncPersonDatabase()
            ),
            Arguments.of(
                new LockPersonDatabase()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void testDatabase(PersonDatabase database) throws InterruptedException {
        var latch2 = new CountDownLatch(2);

        Thread thread2 = new Thread(() -> {
            database.add(newPerson);
            latch2.countDown();
        });

        Thread thread3 = new Thread(() -> {
            database.delete(5);
            latch2.countDown();
        });

        thread2.start();
        thread3.start();

        latch2.await();

        thread2.join();
        thread3.join();

        var latch = new CountDownLatch(1);

        Thread thread1 = new Thread(() -> {

            var persons_by_name = database.findByName("Anton");
            var persons_by_address = database.findByAddress("11/1");
            var persons_by_phone = database.findByPhone("4-444-444-11-11");

            var cnt =
                (persons_by_address == null || persons_by_address.isEmpty() ? 0 : 1)
                + (persons_by_phone == null || persons_by_phone.isEmpty() ? 0 : 1)
                + (persons_by_name == null || persons_by_name.isEmpty() ? 0 : 1);

            assertTrue(
                cnt == 3 || cnt == 0
            );

            if (cnt != 0) {
                persons_by_address.retainAll(persons_by_name);
                persons_by_address.retainAll(persons_by_phone);
                assertEquals(1, persons_by_address.size());
            }

            latch.countDown();
        });

        thread1.start();

        latch.await();

        thread1.join();

    }
}
