package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Contact implements Comparable<Contact> {
    private final @NotNull String firstName;
    private final String lastName;

    public Contact(String fullName) {
        String[] parsedFullName = fullName.split(" ");

        if (parsedFullName.length == 1) {
            this.firstName = parsedFullName[0];
            this.lastName = null;

        } else if (parsedFullName.length == 2) {
            this.firstName = parsedFullName[0];
            this.lastName = parsedFullName[1];

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(@NotNull Contact o) {

        if (lastName == null || lastName.equals(o.lastName)) {
            return firstName.compareTo(o.firstName);

        } else {
            return lastName.compareTo(o.lastName);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Contact)) {
            return false;
        }

        return firstName.equals(((Contact) obj).firstName)
            && lastName.equals(((Contact) obj).lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
