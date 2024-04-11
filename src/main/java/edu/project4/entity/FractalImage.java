package edu.project4.entity;

public final class FractalImage {

    private final Pixel[][] data;
    private final int height;
    private final int width;

    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = new Pixel();
            }
        }
        return new FractalImage(data, height, width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean doesContainPixel(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        if (!doesContainPixel(x, y)) {
            return null;
        }
        return data[y][x];
    }

    private FractalImage(Pixel[][] data, int height, int width) {
        this.data = data;
        this.height = height;
        this.width = width;
    }

}
