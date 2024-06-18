//TSP


import java.util.Scanner;

public class TravellingSalesman {
    private static int[][] distances;
    private static int numCities;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of cities:");
        numCities = scanner.nextInt();
        distances = new int[numCities][numCities];

        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                distances[i][j] = scanner.nextInt();
            }
        }

        int[] cities = new int[numCities];
        for (int i = 0; i < numCities; i++) {
            cities[i] = i;
        }

        int minDistance = Integer.MAX_VALUE;
        int[] bestRoute = null;

        do {
            int currentDistance = calculateRouteDistance(cities);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                bestRoute = cities.clone();
            }
        } while (nextPermutation(cities));

        System.out.println("The shortest route has a distance of: " + minDistance);
        System.out.print("Route: ");
        for (int city : bestRoute) {
            System.out.print((city + 1) + " ");
        }
        System.out.println((bestRoute[0] + 1));

        scanner.close();
    }

    private static int calculateRouteDistance(int[] cities) {
        int distance = 0;
        for (int i = 0; i < cities.length - 1; i++) {
            distance += distances[cities[i]][cities[i + 1]];
        }
        distance += distances[cities[cities.length - 1]][cities[0]];
        return distance;
    }

    private static boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        swap(array, i, j);

        reverse(array, i + 1, array.length - 1);
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
