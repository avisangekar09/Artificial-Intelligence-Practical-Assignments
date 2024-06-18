//8-QUEEN


public class EightQueensProblem {
    private static final int N = 8;
    private static int[][] board = new int[N][N];

    public static void main(String[] args) {
        solveQueensProblem(0);
        printBoard();
    }

    private static boolean solveQueensProblem(int col) {
        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;
                if (solveQueensProblem(col + 1)) {
                    return true;
                }
                board[i][col] = 0; // Backtrack
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col) {
        int i, j;

        // Check this row on left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
