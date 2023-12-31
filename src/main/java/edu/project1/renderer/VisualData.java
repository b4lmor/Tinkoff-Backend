package edu.project1.renderer;

@SuppressWarnings("MultipleStringLiterals")
public class VisualData {
    private VisualData() {
    }

    public static final String[] HANGMAN_GREETING = {
        " _                                             ",
        "| |                                            ",
        "| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  ",
        "| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ ",
        "| | | | (_| | | | | (_| | | | | | | (_| | | | |",
        "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|",
        "                    __/ |                      ",
        "                   |___/                       ",
        "                                               "
    };

    public static final String[][] HANGMAN_STAGES = {
        {
            "  +---+",
            "  |   |",
            "      |",
            "      |",
            "      |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            "      |",
            "      |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            "  |   |",
            "      |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            " /|   |",
            "      |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            " /|\\  |",
            "      |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            " /|\\  |",
            " /    |",
            "      |",
            "========="
        },

        {
            "  +---+",
            "  |   |",
            "  O   |",
            " /|\\  |",
            " / \\  |",
            "      |",
            "========="
        }
    };
}
