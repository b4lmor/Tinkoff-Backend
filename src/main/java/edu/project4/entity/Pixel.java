package edu.project4.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private AtomicInteger hitCount;

    public Pixel(int red, int green, int blue, int hitCount) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = new AtomicInteger(hitCount);
    }

    public Pixel() {
        this(0, 0, 0, 0);
    }


    synchronized public void setRed(int red) {
        this.red = red;
    }

    synchronized public void setGreen(int green) {
        this.green = green;
    }

    synchronized public void setBlue(int blue) {
        this.blue = blue;
    }

    public void incrementHitCount() {
        this.hitCount.incrementAndGet();
    }

    synchronized public int getRed() {
        return red;
    }

    synchronized public int getGreen() {
        return green;
    }

    synchronized public int getBlue() {
        return blue;
    }

    public int getHitCount() {
        return hitCount.get();
    }
}
