package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VideoLengthTest {
    @Test
    @DisplayName("Parse video length from 'mm:ss', mm > 0, ss > 0")
    void parseVideoLength_CaseMinAndSec() {
        int result;
        String rawVideoLength;

        rawVideoLength = "10:05";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(10 * 60 + 5);

        rawVideoLength = "55:11";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(55 * 60 + 11);
    }

    @Test
    @DisplayName("Parse video length from 'mm:ss', mm = 0, ss > 0")
    void parseVideoLength_CaseSec() {
        int result;
        String rawVideoLength;

        rawVideoLength = "00:08";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(8);

        rawVideoLength = "00:22";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(22);
    }

    @Test
    @DisplayName("Parse video length from 'mm:ss', mm > 0, ss = 0")
    void parseVideoLength_CaseMin() {
        int result;
        String rawVideoLength;

        rawVideoLength = "40:00";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(40 * 60);

        rawVideoLength = "33:00";
        result = VideoLength.parseVideoLength(rawVideoLength);

        assertThat(result)
            .isEqualTo(33 * 60);
    }
}
