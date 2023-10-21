package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BackwardIterator<T> implements Iterator<T> {
    private final ListIterator<T> listIterator;

    public BackwardIterator(Collection<T> collection) {
        List<T> list = List.copyOf(collection);
        this.listIterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override
    public T next() {
        return listIterator.previous();
    }
}
