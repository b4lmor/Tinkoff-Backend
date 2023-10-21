package edu.hw3.task5;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw3.task5.ContactList.SortType.ASCENDING;
import static edu.hw3.task5.ContactList.SortType.DESCENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListTest {

    @ParameterizedTest
    @DisplayName("test Contact List")
    @MethodSource("provideValues")
    public void testContactList(List<String> inputNames,
                                ContactList.SortType inputSortType,
                                List<Contact> expectedResult) {

        List<Contact> result = ContactList.parseContacts(inputNames, inputSortType);

        assertEquals(
            result,
            expectedResult
        );

    }

    private static Stream<Arguments> provideValues() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "John Locke",
                    "Thomas Aquinas",
                    "David Hume",
                    "Rene Descartes"
                ),
                ASCENDING,
                List.of(
                    new Contact("Thomas Aquinas"),
                    new Contact("Rene Descartes"),
                    new Contact("David Hume"),
                    new Contact("John Locke")
                )
            ),
            Arguments.of(
                List.of(
                    "Paul Erdos",
                    "Leonhard Euler",
                    "Carl Gauss"
                    ),
                DESCENDING,
                List.of(
                    new Contact("Carl Gauss"),
                    new Contact("Leonhard Euler"),
                    new Contact("Paul Erdos")
                )
            ),
            Arguments.of(
                List.of(),
                DESCENDING,
                List.of()
            ),
            Arguments.of(
                null,
                DESCENDING,
                List.of()
            )
        );
    }
}
