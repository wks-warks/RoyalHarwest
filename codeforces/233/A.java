//Codeforces 233A 
import java.util.Scanner;

public class CF233A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int permutationSize = SC.nextInt();
        if ((permutationSize & 1) == 1) {
            System.out.println(-1);
            return;
        }
        int[] permutation = getPerfectPermutation(permutationSize);
        out(permutation);
    }

    // Computes and returns perfect permutation (existence checked beforehand)
    static int[] getPerfectPermutation(int permutationSize) {
        int[] permutation = new int[permutationSize];
        for (int i = 0; i < permutationSize; ++i)
            if ((i & 1) == 0)
                permutation[i] = i + 2;
            else
                permutation[i] = i;
        return permutation;
    }

    // Prints output corresponding to array
    static void out(int[] array) {
        for (int i = 0; i < array.length; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}
