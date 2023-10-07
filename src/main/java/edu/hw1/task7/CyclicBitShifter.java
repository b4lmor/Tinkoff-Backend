package edu.hw1.task7;

public class CyclicBitShifter {

    private CyclicBitShifter() {
        throw new IllegalStateException("Utility class");
    }

    private static int[] getBits(int number) {
        int numberCopy = number;
        int numBits = 0;
        int temp = number;

        while (temp != 0) {
            numBits++;
            temp >>= 1;
        }

        int[] bits = new int[numBits];

        for (int i = numBits - 1; i >= 0; i--) {
            bits[i] = numberCopy & 1;
            numberCopy >>= 1;
        }

        return bits;
    }

    private static int[] cyclicRightShift(int[] array, int shift) {
        int shiftCopy = shift;
        int length = array.length;

        shiftCopy = shiftCopy % length;
        if (shiftCopy < 0) {
            shiftCopy = length + shiftCopy;
        }

        int[] shiftedArray = new int[length];

        for (int i = 0; i < length; i++) {
            int newIndex = (i + shiftCopy) % length;
            shiftedArray[newIndex] = array[i];
        }

        return shiftedArray;
    }

    private static int getNumber(int[] bits) {
        int number = 0;

        for (int bit : bits) {
            number <<= 1;
            number |= bit;
        }

        return number;
    }

    public static int rotateRight(int n, int shift) {
        int[] bits = getBits(n);
        int[] shiftedBits = cyclicRightShift(bits, shift);
        return getNumber(shiftedBits);
    }

    public static int rotateLeft(int n, int shift) {
        return rotateRight(n, -shift);
    }

}
