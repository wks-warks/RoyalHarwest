// Codeforces 69A
import java.util.Scanner;

public class CF69A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numVectors = SC.nextInt(); // Number of vectors
        int[][] vectors = new int[numVectors][3];
        for (int v = 0; v < numVectors; ++v)
            for (int i = 0; i < 3; ++i)
                vectors[v][i] = SC.nextInt();
        boolean inEquilibrium = checkEquilibrium(vectors);
        out(inEquilibrium);
    }

    // Checks if sum of vectors is 0
    static boolean checkEquilibrium(int[][] vectors) {
        int[] forces = new int[3]; // Net force along the three directions
        for (int v = 0; v < vectors.length; ++v)
            for (int i = 0; i < 3; ++i)
                forces[i] += vectors[v][i];
        
        for (int i = 0; i < 3; ++i)
            if (forces[i] != 0) // if net-force along any direction is non-zero
                return false; // body is NOT in equilibrium
        return true; // forces[i] = 0 for all i => body in equilibrium
    }

    // Prints output based on condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}