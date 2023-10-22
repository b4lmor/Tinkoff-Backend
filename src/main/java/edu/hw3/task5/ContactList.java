package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactList {

    private ContactList() {
    }

    public enum SortType {
        ASCENDING,
        DESCENDING
    }

    public static List<Contact> parseContacts(List<String> rawNames, SortType sortType) {

        if (rawNames == null) {
            return List.of();
        }

        List<Contact> contacts = rawNames.stream()
            .map(Contact::new)
            .toList();

        return switch (sortType) {
            case ASCENDING -> contacts.stream()
                .sorted()
                .toList();

            case DESCENDING -> contacts.stream()
                .sorted(
                    Comparator.reverseOrder()
                )
                .toList();
        };
    }
}
