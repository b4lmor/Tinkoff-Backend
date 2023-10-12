package edu.hw2.task2;

public class Rectangle {
    protected int width = 0;
    protected int height = 0;

    Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    double calculateArea() {
        return width * height;
    }
}
