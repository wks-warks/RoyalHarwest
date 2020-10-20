// Codeforces 577A
import java.util.Scanner;

public class CF577A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int dim = SC.nextInt(); // Dimension of the table
        int number = SC.nextInt(); // Number whose occurrences are to be counted
        int occurrences = countOccurrences(dim, number);
        System.out.println(occurrences);
    }

    // Counts the number of occurrences for the number passed
    static int countOccurrences(int dim, int number) {
        if ((long)dim*dim < number)
            return 0;
        else {
            int occurrences = 0;
            int i;
            for (i = LIG(number, dim); i*i<number; ++i)
                if (number % i == 0)
                    occurrences += 2; // Accounting for symmetry
            if (i*i == number)
                occurrences += 1;
            return occurrences;
        }
    }

    // Returns least integer greater than (double) numerator / denominator
    static int LIG(int numer, int denom) {
        return (numer+denom-1)/denom;
    }
}