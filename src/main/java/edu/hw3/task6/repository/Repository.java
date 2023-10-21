package edu.hw3.task6.repository;

public interface Repository<T extends Comparable<T>> {
    void add(T obj);

    void remove(T obj);

    T pickMax();
}
