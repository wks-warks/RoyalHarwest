// Codeforces 519B
import java.util.Scanner;
import java.util.stream.*;

public class CF519B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int errors = SC.nextInt();
        int[] originalErrors = new int[errors];
        for (int i = 0; i < originalErrors.length; ++i)
            originalErrors[i] = SC.nextInt();

        int[] afterRemoving1 = new int[errors-1];
        for (int i = 0; i < afterRemoving1.length; ++i)
            afterRemoving1[i] = SC.nextInt();
        
        int[] afterRemoving2 = new int[errors-2];
        for (int i = 0; i < afterRemoving2.length; ++i)
            afterRemoving2[i] = SC.nextInt();

        int firstError = getError(originalErrors, afterRemoving1);
        int secondError = getError(afterRemoving1, afterRemoving2);
        out(firstError, secondError);
    }

    // Computes and returns the error based on previous errors and present errors
    static int getError(int[] previous, int[] now) {
        long errSumPrev = IntStream.of(previous).sum(); // Sum of error numbers previously
        long errSumNow = IntStream.of(now).sum(); // Sum of error numbers now
        return (int)(errSumPrev - errSumNow);
    }

    // Prints output in desired format
    static void out(int first, int second) {
        System.out.println(first);
        System.out.println(second);
    }
}