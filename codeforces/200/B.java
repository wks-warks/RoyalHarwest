// Codeforces 200B
import java.util.Scanner;

public class CF200B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numJuices = SC.nextInt();
        int[] orangeFraction = new int[numJuices];
        for (int j = 0; j < numJuices; ++j)
            orangeFraction[j] = SC.nextInt();
        double averagedOrangeFraction = computeAverage(orangeFraction);
        System.out.println(averagedOrangeFraction);
    }

    // Computes and returns the average of given integers
    static double computeAverage(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; ++i)
            sum += numbers[i];
        double average = (double) sum / numbers.length;
        return average;
    }
}