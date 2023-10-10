package edu.hw1.task1;

import org.jetbrains.annotations.NotNull;

public class VideoLength {
    private static final String RAW_VIDEO_LENGTH_REGEX_PATTERN = "\\d+:[0-5][0-9]";
    private static final int SECONDS_IN_MINUTE = 60;

    private VideoLength() {
    }

    public static int minutesToSeconds(@NotNull String rawVideoLength) {
        if (!isValidRawVideoLength(rawVideoLength)) {
            throw new IllegalArgumentException();
        }

        String[] minAndSec = rawVideoLength.split(":");
        int minutes = Integer.parseInt(minAndSec[0]);
        int seconds = Integer.parseInt(minAndSec[1]);

        return convertMinutesToSeconds(minutes) + seconds;
    }

    private static boolean isValidRawVideoLength(String rawVideoLength) {
        return rawVideoLength.matches(RAW_VIDEO_LENGTH_REGEX_PATTERN);
    }

    private static int convertMinutesToSeconds(int minutes) {
        return minutes * SECONDS_IN_MINUTE;
    }
}
