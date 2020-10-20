// Codeforces 1342A
import java.util.Scanner;
    
public class CF1342A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int num1 = SC.nextInt();
            int num2 = SC.nextInt();
            int costOne = SC.nextInt(); // cost to change one number by 1
            int decreaseCost = SC.nextInt(); // cost to decrease both numbers simultaneously
            long costToZero = getCostToZero(num1, num2, costOne, decreaseCost);
            System.out.println(costToZero);
        }
    }
    
    // Computes and returns minimum cost to zero
    static long getCostToZero(int num1, int num2, int costOne, int decreaseCost) {
        int smaller = Math.min(num1, num2);
        int larger = num1 + num2 - smaller;
        long cost = Math.min((long)(num1 + num2) * costOne, (long)(larger-smaller) * costOne + (long)smaller*decreaseCost);
        return cost;
    }
}