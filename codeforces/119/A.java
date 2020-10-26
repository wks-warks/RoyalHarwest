//Codeforces 119A 
import java.util.Scanner;

public class CF119A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int simon = SC.nextInt(); // Simon's number
        int antisimon = SC.nextInt(); // Antisimon's number
        int stonesInPile = SC.nextInt();
        int simulationResult = getResult(simon, antisimon, stonesInPile);
        System.out.println(simulationResult);
    }

    // Computes and returns 1 or 0 based on who wins
    static int getResult(int simon, int antisimon, int stonesInPile) {
        int i = 1;
        while (true) {
            if (stonesInPile == 0)
                return i % 2;
            if (i % 2 == 1) { // Simon's turn
                int pick = MathOperations.GCD(simon, stonesInPile);
                stonesInPile -= pick;
            }
            else { // Antisimon's turn
                int pick = MathOperations.GCD(antisimon, stonesInPile);
                stonesInPile -= pick;
            }
            i += 1;
        }
    }
}

class MathOperations {
    // Returns GCD of two numbers
    static int GCD(int num1, int num2) {
        if (num1 == 0)
            return num2;
        return GCD(num2%num1, num1);
    }
}
