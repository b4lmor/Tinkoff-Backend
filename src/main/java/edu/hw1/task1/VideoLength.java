package edu.hw1.task1;

public class VideoLength {
    private static final String RAW_VIDEO_LENGTH_REGEX_PATTERN = "\\d{2}:[0-5][0-9]";
    private static final int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
        throw new IllegalStateException("Utility class");
    }

    private static boolean isValidRawVideoLength(String rawVideoLength) {
        return rawVideoLength.matches(RAW_VIDEO_LENGTH_REGEX_PATTERN);
    }

    private static int convertMinutesToSeconds(int minutes) {
        return minutes * SECONDS_IN_MINUTE;
    }

    public static int parseVideoLength(String rawVideoLength) {
        if (!isValidRawVideoLength(rawVideoLength)) {
            throw new IllegalArgumentException();
        }

        String[] minAndSec = rawVideoLength.split(":");
        final int minutes = Integer.parseInt(minAndSec[0]);
        final int seconds = Integer.parseInt(minAndSec[1]);

        return convertMinutesToSeconds(minutes) + seconds;
    }
}
