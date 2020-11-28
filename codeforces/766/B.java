//Codeforces 766B 
import java.util.Scanner;
import java.util.Arrays;

public class CF766B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int lines = SC.nextInt();
        int[] lineLength = new int[lines];
        for (int ln = 0; ln < lines; ++ln)
            lineLength[ln] = SC.nextInt();
        boolean triangleFormable = checkFormability(lineLength);
        out(triangleFormable);
    }

    // Computes and returns whether a triangle (non-degenerate) can be formed with the given lines
    static boolean checkFormability(int[] lineLength) {
        Arrays.sort(lineLength);
        for (int i = 2; i < lineLength.length; ++i)
            if (lineLength[i-2] + lineLength[i-1] > lineLength[i])
                return true;
        return false;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition ? "YES" : "NO");
    }
}
