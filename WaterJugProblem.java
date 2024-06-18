//water jug problem 


import java.util.*;

class State {
    int jug1;
    int jug2;

    State(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    boolean equals(State other) {
        return this.jug1 == other.jug1 && this.jug2 == other.jug2;
    }

    String toStringState() {
        return "(" + jug1 + ", " + jug2 + ")";
    }
}

public class WaterJugProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the capacity of Jug 1:");
        int capacity1 = scanner.nextInt();

        System.out.println("Enter the capacity of Jug 2:");
        int capacity2 = scanner.nextInt();

        System.out.println("Enter the desired amount of water (d):");
        int desired = scanner.nextInt();

        solveWaterJugProblem(capacity1, capacity2, desired, 0, 0, new HashSet<>());
    }

    private static void solveWaterJugProblem(int capacity1, int capacity2, int desired, int jug1, int jug2, Set<State> visited) {
        State currentState = new State(jug1, jug2);

        if (jug1 == desired || jug2 == desired) {
            System.out.println("Solution found: " + currentState.toStringState());
            return;
        }

        if (visited.contains(currentState)) {
            return;
        }

        visited.add(currentState);

        // Fill Jug 1
        if (jug1 < capacity1) {
            solveWaterJugProblem(capacity1, capacity2, desired, capacity1, jug2, visited);
        }

        // Fill Jug 2
        if (jug2 < capacity2) {
            solveWaterJugProblem(capacity1, capacity2, desired, jug1, capacity2, visited);
        }

        // Empty Jug 1
        if (jug1 > 0) {
            solveWaterJugProblem(capacity1, capacity2, desired, 0, jug2, visited);
        }

        // Empty Jug 2
        if (jug2 > 0) {
            solveWaterJugProblem(capacity1, capacity2, desired, jug1, 0, visited);
        }

        // Pour Jug 1 to Jug 2
        int pour1to2 = Math.min(jug1, capacity2 - jug2);
        if (pour1to2 > 0) {
            solveWaterJugProblem(capacity1, capacity2, desired, jug1 - pour1to2, jug2 + pour1to2, visited);
        }

        // Pour Jug 2 to Jug 1
        int pour2to1 = Math.min(jug2, capacity1 - jug1);
        if (pour2to1 > 0) {
            solveWaterJugProblem(capacity1, capacity2, desired, jug1 + pour2to1, jug2 - pour2to1, visited);
        }
    }
}
