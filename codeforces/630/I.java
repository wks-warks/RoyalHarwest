//Codeforces 630I 
import java.util.Scanner;

public class CF630I {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int successiveSameMake = SC.nextInt();
        long ways = countWays(successiveSameMake);
        System.out.println(ways);
    }

    // Counts and returns the number of ways to get the desired composition of cars
    static long countWays(int successiveSameMake) {
        long answer = 2 * 3 * exp(4, successiveSameMake-3);
        for (int i = 1; i < successiveSameMake - 2; i += 1) {
            answer += 3 * 3 * exp(4, successiveSameMake-4);
        }
        return 4 * answer;
    }

    // Computes and returns exp(base, power)
    static long exp(long base, long power) {
        if (power == 0)
            return 1;
        long digit = 1;
        long answer = 1;
        long mul = base;
        while (digit <= power) {
            if ((digit & power) > 0) {
                answer *= mul;
            }
            mul *= mul;
            digit <<= 1;
        }
        return answer;
    }
}
