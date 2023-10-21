package edu.hw3.task7;

import java.util.Comparator;

public class NullComparator<T> implements Comparator<T> {
    private final Comparator<T> comparator;

    public NullComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {

        if (o1 == null && o2 == null) {
            return 0;

        } else if (o1 == null) {
            return -1;

        } else if (null == o2) {
            return 1;

        } else {
            return comparator.compare(o1, o2);
        }
    }
}
