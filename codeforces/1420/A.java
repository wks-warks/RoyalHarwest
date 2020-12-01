//Codeforces 1420A 
import java.util.Scanner;
import java.util.Arrays;

public class CF1420A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int cubes = SC.nextInt();
            int[] volumes = new int[cubes];
            for (int c = 0; c < cubes; ++c) {
                volumes[c] = SC.nextInt();
            }
            boolean sortable = checkSortability(volumes);
            out(sortable);
        }        
    }

    // Returns and tells whether or not the given array can be sorted
    static boolean checkSortability(int[] array) {
        int[] copy = array.clone();
        Arrays.sort(copy);
        
        for (int i = 1; i < copy.length; ++i) {
            if (copy[i] == copy[i-1]) {
                return true;
            }
        }

        for (int i = 0; i < copy.length; ++i) {
            if (copy[i] != array[array.length-i-1]) {
                return true;
            }
        }

        return false; // Sorted in descending order, with all elements pairwise distinct
    }

    // Prints output corresponding to the boolean condition
    static void out(boolean condition) {
        System.out.println(condition? "YES" : "NO");
    }
}