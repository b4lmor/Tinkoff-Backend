package edu.hw1.task8;

import org.jetbrains.annotations.NotNull;

public class ChessBoard {
    private static final int CHESS_BOARD_SIDE_LENGTH = 8;
    private static final int KNIGHT_ID = 1;
    private static final int[][] KNIGHT_DOWNSIDE_RANGE_SHIFTS = {
        {+2, -1},
        {-2, -1},
        {+1, -2},
        {-1, -2}
    };

    private ChessBoard() {
    }

    public static boolean knightBoardCapture(int @NotNull [] @NotNull [] board) {
        if (!isBoardValid(board)) {
            throw new IllegalArgumentException();
        }

        for (int x = 0; x < CHESS_BOARD_SIDE_LENGTH; x++) {
            for (int y = 0; y < CHESS_BOARD_SIDE_LENGTH; y++) {
                if (board[x][y] != KNIGHT_ID) {
                    continue;
                }
                for (int[] spot : KNIGHT_DOWNSIDE_RANGE_SHIFTS) {
                    if (isPieceOnSpot(x + spot[0], y + spot[1], board, KNIGHT_ID)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isBoardValid(int[][] board) {
        if (board.length != CHESS_BOARD_SIDE_LENGTH) {
            return false;
        }
        for (int[] line : board) {
            if (line.length != CHESS_BOARD_SIDE_LENGTH) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPieceOnSpot(int x, int y, int[][] board, int pieceID) {
        if (x < 0 || x >= CHESS_BOARD_SIDE_LENGTH
         || y < 0 || y >= CHESS_BOARD_SIDE_LENGTH) {
            return false;
        }
        return board[x][y] == pieceID;
    }

}
