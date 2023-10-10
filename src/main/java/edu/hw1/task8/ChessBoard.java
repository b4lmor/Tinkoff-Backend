package edu.hw1.task8;

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

    public static boolean knightBoardCapture(int[][] board) {
        if (isNotValidBoard(board)) {
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

    private static boolean isNotValidBoard(int[][] board) {
        if (board == null || board.length != CHESS_BOARD_SIDE_LENGTH) {
            return true;
        }
        for (int[] line : board) {
            if (line == null || line.length != CHESS_BOARD_SIDE_LENGTH) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPieceOnSpot(int x, int y, int[][] board, int pieceID) {
        if (x < 0 || x >= CHESS_BOARD_SIDE_LENGTH
         || y < 0 || y >= CHESS_BOARD_SIDE_LENGTH) {
            return false;
        }
        return board[x][y] == pieceID;
    }

}
