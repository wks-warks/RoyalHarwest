// Codeforces 1030A
import java.util.Scanner;

public class CF1030A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numProbs = SC.nextInt(); // Number of problems
        boolean[] opinions = new boolean[numProbs];
        for (int p = 0; p < numProbs; ++p) {
            byte difficulty = SC.nextByte();
            if (difficulty == 0)
                opinions[p] = true;
            else
                opinions[p] = false;
        }
        boolean allEasy = checkIfAllEasy(opinions);
        out(allEasy);
    }

    // Checks and returns boolean that tells whether or not all opinions deem the problem easy
    static boolean checkIfAllEasy(boolean[] opinions) {
        for (int o = 0; o < opinions.length; ++o)
            if (!opinions[o])
                return false;
        return true; // All opinions deemed the problem to be easy
    }
    
    // Prints output corresponding to whether or not all problems are easy
    static void out(boolean allEasy) {
        if (allEasy)
            System.out.println("Easy");
        else
            System.out.println("Hard");
    }
}