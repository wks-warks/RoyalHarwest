// Codeforces 1343B
import java.util.Scanner;

public class CF1343B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt(); // Number of testCases
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            checkAndPrint(n); // Prints desired output
        }
    }

    // Performs check and prints output accordingly
    static void checkAndPrint(int n) {
        boolean possible = n % 4 == 0;
        if (possible) {
            System.out.println("YES");
            int[] solutionArr = getSolutionArr(n); // Gets solution array for size n
            out(solutionArr);
        }
        else
            System.out.println("NO");
    }

    // Creates and returns solution array for size n
    static int[] getSolutionArr(int n) {
        int[] solution = new int[n]; // Solution Array
        // Adding even elements
        int mid = n/2;
        for (int i = 0; i < mid; ++i)
            solution[i] = 2*(i+1);
        // Adding odd elements
        solution[mid] = 3*(n/2) - 1; // n/2-th odd number + n/2 = 2*(n/2) - 1 + n/2
        for (int i = 1; i < mid; ++i)
            solution[mid+i] = 2*i - 1;
        return solution;
    }

    // Prints output corresponding to given array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}