package edu.hw1.task1;

public class VideoLength {
    private static final String RAW_VIDEO_LENGTH_REGEX_PATTERN = "\\d+:[0-5][0-9]";
    private static final int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
    }

    public static int minutesToSeconds(String rawVideoLength) {
        if (isNotValidRawVideoLength(rawVideoLength)) {
            throw new IllegalArgumentException();
        }

        String[] minAndSec = rawVideoLength.split(":");
        int minutes = Integer.parseInt(minAndSec[0]);
        int seconds = Integer.parseInt(minAndSec[1]);

        return convertMinutesToSeconds(minutes) + seconds;
    }

    private static boolean isNotValidRawVideoLength(String rawVideoLength) {
        return rawVideoLength == null
            || !rawVideoLength.matches(RAW_VIDEO_LENGTH_REGEX_PATTERN);
    }

    private static int convertMinutesToSeconds(int minutes) {
        return minutes * SECONDS_IN_MINUTE;
    }
}
