import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        task1();
        task2();
    }
    // task 1
    public static void task1() {
        System.out.print("\n\n\nTHIS IS TASK 1!!!\n");
        // variables that we are calculating
        double x = 0;
        double y = 0;

        // iterating over values of a and b vars
        for (float a = 0; a <= 5; a += 0.5) {
            for (float b = -3; b <= 1; b += 0.1) {
                if (-10 > b) {
                    x = Math.pow(a, 7) + 45 * b + a * b; // formulae for x
                    y = calcY(x); // calculating y based on x
                } else if ((-10 <= b) && (b < 0)) {
                    x = (45 * a) / (36 * b) + 19 * a; // formulae for x
                    y = calcY(x); // calculating y based on x
                } else if ((5 < b) && (b < 8)) {
                    x = Math.pow(8 * Math.pow(a, 3) + 41 * Math.pow(a, 2) - 7 * Math.pow(b, 9), 1/5); // formulae for x
                    y = calcY(x); // calculating y based on x
                } else if (8 <= b) {
                    x = Math.pow(a, 5); // formulae for x
                    y = calcY(x); // calculating y based on x
                } else {
                    x = 0; // formulae for x
                    y = calcY(x); // calculating y based on x
                }

                System.out.printf("y = %f         x = %f\n", y, x);
                System.out.print("===================================\n");
            }
        }
    }
    public static double calcY(double x) {
        if (x > 3) {
            return (x - 78) / (Math.abs(x - 56) + 1); // formulae for y
        } else {
            return (Math.pow(x - 2, 3)) / (Math.log(4 - x)); // formulae for y
        }
    }
    //task 2
    public static void task2() {
        System.out.print("\n\n\nTHIS IS TASK 2!!!\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = sc.nextInt(); // taking N as an input
        int SIZE = N + 15; // defining size of a matrix
        int[][] matrix = new int[SIZE][SIZE];
        Random random = new Random(); // filling matrix with random numbers in range [-5;5]
        for (int i = 0; i < N + 15; i++) {
            for (int j = 0; j < N + 15; j++) {
                matrix[i][j] = random.nextInt(10) - 5;
            }
        }
        printMatrix(matrix, SIZE, "This is origin matrix"); // printing created matrix
        int upperSum = 0;
        int lowerSum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i < j) {
                    upperSum += matrix[i][j];
                } else if (i > j){
                    lowerSum += matrix[i][j];
                }
            }
        }
        System.out.printf("UPPER SUM: %d\nLOWER SUM: %d\n", upperSum, lowerSum);
        if (upperSum > lowerSum) {
            matrix = sortMatrix(matrix, "DSC");
        } else {
            matrix = sortMatrix(matrix, "ASC");
        }
        printMatrix(matrix, SIZE, "This is sorted matrix:"); // printing created matrix

    }
    public static void printMatrix(int[][] matrix, int SIZE, String text) {
        System.out.print("===============\n");
        System.out.println(text+"\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int[][] sortMatrix(int[][] matrix, String order) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int rowIndex = i;
                int colIndex = j;
                for (int x = i; x < numRows; x++) {
                    for (int y = (x == i ? j : 0); y < numCols; y++) {
                        // if order is ASCENDING sorting from lowest to highest
                        // if order is DESCENDING sorting from highest to lowest
                        if ((order.equals("ASC")) && (matrix[x][y] < matrix[rowIndex][colIndex])) {
                            rowIndex = x;
                            colIndex = y;
                        } else if ((order.equals("DSC")) && (matrix[x][y] > matrix[rowIndex][colIndex])) {
                            rowIndex = x;
                            colIndex = y;
                        }
                    }
                }
                // Swap the minimum element with the current element
                int temp = matrix[i][j];
                matrix[i][j] = matrix[rowIndex][colIndex];
                matrix[rowIndex][colIndex] = temp;
            }
        }
        return matrix;
    }
}

