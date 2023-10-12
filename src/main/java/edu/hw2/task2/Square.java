package edu.hw2.task2;

public class Square extends Rectangle {
    @Override
    Rectangle setWidth(int w) {
        if (w == this.height) {
            return this;
        }
        return (new Rectangle())
            .setWidth(w)
            .setHeight(this.height);
    }

    @Override
    Rectangle setHeight(int h) {
        if (h == this.width) {
            return this;
        }
        return (new Rectangle())
            .setHeight(h)
            .setWidth(this.width);
    }

    void resize(int side) {
        this.height = side;
        this.width = side;
    }

    public Square() {
    }

    public Square(int side) {
        resize(side);
    }
}
