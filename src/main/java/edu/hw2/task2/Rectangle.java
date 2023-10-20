package edu.hw2.task2;

public class Rectangle {
    protected int width = 0;
    protected int height = 0;

    public Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public double calculateArea() {
        return width * height;
    }
}
