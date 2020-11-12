//Codeforces 1405A 
import java.util.Scanner;

public class CF1405A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int permLen = SC.nextInt();
            int[] permutation = new int[permLen];
            for (int i = 0; i < permLen; ++i)
                permutation[i] = SC.nextInt();
            int solution[] = getSolution(permutation);
            out(solution);
        }
    }

    // Computes and returns solution permutation
    static int[] getSolution(int[] permutation) {
        int lastIdx = permutation.length-1;
        int[] solution = new int[lastIdx+1];
        for (int i = 0; i * 2 < permutation.length; ++i) {
            solution[i] = permutation[lastIdx-i];
            solution[lastIdx-i] = permutation[i];
        }
        return solution;
    }

    // Prints output permutation
    static void out(int[] solution) {
        for (int i = 0; i < solution.length; ++i)
            System.out.print(solution[i] + " ");
        System.out.println();
    }
}
