//Codeforces 1436A 
import java.util.Scanner;
import java.util.stream.IntStream;

public class CF1436A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLength = SC.nextInt();
            int m = SC.nextInt();

            int[] arr = new int[arrLength];
            for (int i = 0; i < arrLength; ++i)
                arr[i] = SC.nextInt();
                
            out(IntStream.of(arr).sum() == m);
        }
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition ? "YES" : "NO");
    }
}
