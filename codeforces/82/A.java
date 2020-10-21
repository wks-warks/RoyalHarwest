// Codeforces 82A
import java.util.Scanner;

public class CF82A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        String whoDrinksn = getName(n); // Name of the person who drinke the n-th drink
        System.out.println(whoDrinksn);
    }

    // Finds and returns the name of the person who drinks the n-th can of cola
    static String getName(int n) {
        String[] persons = {
            "Sheldon", "Leonard", "Penny", "Rajesh", "Howard"
        };
        int roundsCompleted = computeCompletedRounds(n);
        int drinksUptoFinal = 5 * ((1<<(roundsCompleted)) - 1); // Note that 2 << -1 = 0
        int remainingDrinks = n - drinksUptoFinal;
        int personIdx = getPersonIdx(remainingDrinks, roundsCompleted+1); // Person-Idx considering conditions for final round
        return persons[personIdx];
    }

    // Computes and returns number of rounds completed
    static int computeCompletedRounds(int n) {
        int completed = 0;
        int roundMultiplier = 1;
        int people = 5;
        while (people * roundMultiplier < n) {
            completed += 1;
            n -= people * roundMultiplier;
            roundMultiplier *= 2;
        }
        return completed;
    }
    
    // Computes and returns the index of the person who drinks the last drink
    static int getPersonIdx(int remainingDrinks, int roundNumber) {
        int drinksPerPerson = 1 << (roundNumber-1); // Number of instances of each person in queue in the given round
        int idx = (remainingDrinks-1)/drinksPerPerson;
        return idx;
    }
}