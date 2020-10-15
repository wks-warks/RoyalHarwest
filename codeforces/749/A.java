// Codeforces 749A
import java.util.Scanner;

public class CF749A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int number = SC.nextInt();
        printPrimeDistribution(number);
    }

    // Prints the given number as a sum of prime numbers such that the constituent primes are maximum in number
    static void printPrimeDistribution(int number) {
        // Maximum constituents => Smaller constituents should be preferred. Use as many 2s as possible, and if the number is odd - then use a 3 instead of one 2.
        int primes = number/2; // Number of prime constituents
        System.out.println(primes);
        if ((number & 1) == 1)
            System.out.print(3 + " ");
        else
            System.out.print(2 + " ");
        
        for (int p = 1; p < primes; ++p)
            System.out.print(2 + " ");
    }
}