//8-puzzle


import java.util.*;

class PuzzleState {
    int[][] board;
    int emptyRow, emptyCol;
    int cost;
    int heuristic;
    PuzzleState parent;

    PuzzleState(int[][] board, int emptyRow, int emptyCol, int cost, int heuristic, PuzzleState parent) {
        this.board = board;
        this.emptyRow = emptyRow;
        this.emptyCol = emptyCol;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    static boolean isSolvable(int[][] board) {
        int[] arr = new int[9];
        int idx = 0;
        int inversions = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[idx++] = board[i][j];
            }
        }

        for (int i = 0; i < 9; i++) {
            if (arr[i] == 0) continue;
            for (int j = i + 1; j < 9; j++) {
                if (arr[j] != 0 && arr[i] > arr[j]) {
                    inversions++;
                }
            }
        }

        return inversions % 2 == 0;
    }

    static int heuristic(int[][] board, int[][] goal) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 0) {
                    int value = board[i][j];
                    outer: for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (goal[k][l] == value) {
                                distance += Math.abs(i - k) + Math.abs(j - l);
                                break outer;
                            }
                        }
                    }
                }
            }
        }
        return distance;
    }

    static boolean isGoalState(int[][] board, int[][] goal) {
        return Arrays.deepEquals(board, goal);
    }

    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    static List<PuzzleState> getNeighbors(PuzzleState current, int[][] goal) {
        List<PuzzleState> neighbors = new ArrayList<>();
        int[] rowMoves = { -1, 1, 0, 0 };
        int[] colMoves = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int newRow = current.emptyRow + rowMoves[i];
            int newCol = current.emptyCol + colMoves[i];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int[][] newBoard = new int[3][3];
                for (int r = 0; r < 3; r++) {
                    newBoard[r] = current.board[r].clone();
                }
                newBoard[current.emptyRow][current.emptyCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;

                neighbors.add(new PuzzleState(newBoard, newRow, newCol, current.cost + 1, heuristic(newBoard, goal), current));
            }
        }

        return neighbors;
    }

    static void solvePuzzle(int[][] start, int[][] goal) {
        if (!isSolvable(start)) {
            System.out.println("The puzzle is not solvable.");
            return;
        }

        PriorityQueue<PuzzleState> openSet = new PriorityQueue<>(new Comparator<PuzzleState>() {
            @Override
            public int compare(PuzzleState s1, PuzzleState s2) {
                return (s1.cost + s1.heuristic) - (s2.cost + s2.heuristic);
            }
        });
        Set<PuzzleState> closedSet = new HashSet<>();

        int emptyRow = 0, emptyCol = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (start[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                    break;
                }
            }
        }

        PuzzleState initialState = new PuzzleState(start, emptyRow, emptyCol, 0, heuristic(start, goal), null);
        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            PuzzleState current = openSet.poll();

            if (isGoalState(current.board, goal)) {
                Stack<PuzzleState> path = new Stack<>();
                while (current != null) {
                    path.push(current);
                    current = current.parent;
                }

                while (!path.isEmpty()) {
                    PuzzleState state = path.pop();
                    printBoard(state.board);
                    System.out.println();
                }
                return;
            }

            closedSet.add(current);

            for (PuzzleState neighbor : getNeighbors(current, goal)) {
                if (!containsState(closedSet, neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }

        System.out.println("No solution found.");
    }

    static boolean containsState(Set<PuzzleState> set, PuzzleState state) {
        for (PuzzleState s : set) {
            if (Arrays.deepEquals(s.board, state.board)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] start = new int[3][3];
        int[][] goal = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };

        System.out.println("Enter the start state of the puzzle (use 0 for the empty space):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start[i][j] = scanner.nextInt();
            }
        }

        solvePuzzle(start, goal);
    }
}
