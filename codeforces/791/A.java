// Codeforces 791A
import java.util.Scanner;

public class CF791A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int weightLimak = SC.nextInt();
        int weightBob = SC.nextInt();
        int yearsToOvertake = computeYearsToOvertake(weightLimak, weightBob);
        System.out.println(yearsToOvertake);
    }

    // Computes years required by Limak to weigh strictly more than Bob
    static int computeYearsToOvertake(int weightLimak, int weightBob) {
        int years = 0;
        while (weightLimak <= weightBob) {
            years += 1;
            weightLimak *= 3;
            weightBob *= 2;
        }
        return years;
    }
}