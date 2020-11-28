//Codeforces 476B 
import java.util.Scanner;

public class CF476B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String originalCommands = SC.next();
        String recognizedCommands = SC.next();
        double arrivalProbability = computeArrivalProbability(originalCommands, recognizedCommands);
        System.out.println(arrivalProbability);
    }

    // Computes and returns arrival probability
    static double computeArrivalProbability(String originalCommands, String recognizedCommands) {
        int destination = 0;
        for (char c : originalCommands.toCharArray()) {
            if (c == '+')
                destination += 1;
            else
                destination -= 1;
        }

        int finalPos = 0;
        int uncertain = 0;
        for (char c : recognizedCommands.toCharArray()) {
            if (c == '+')
                finalPos += 1;
            else if (c == '-')
                finalPos -= 1;
            else
                uncertain += 1;
        }

        int difference = Math.abs(destination - finalPos);
        if (difference > uncertain)
            return 0.0;
        else {
            return nCr(uncertain, (uncertain - difference)/2) / (1 << uncertain);
        }
    }

    // returns nCr value for given n, r
    static double nCr(int n, int r) {
        int ans = 1;
        for (int i = r + 1; i <= n; ++i)
            ans *= i;
        for (int i = 1; i <= n-r; ++i)
            ans /= i;
        return (double) ans;
    }
}
