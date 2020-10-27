//Codeforces 550A 
import java.util.Scanner;

public class CF550A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String s = SC.next();
        boolean doesContain = checkIfContains(s);
        out (doesContain);
    }

    // Checks and tells whether or not non-overlapping instances of AB, BA exist in the string
    static boolean checkIfContains(String s) {
        int countAB = 0;
        int[] idxAB = new int[2];
        int countBA = 0;
        int[] idxBA = new int[2];
        for (int i = 0; i < s.length()-1; ++i) {
            if (s.charAt(i) == 'A')
                if (s.charAt(i+1) == 'B') {
                    idxAB[countAB%2] = i;
                    countAB += 1;
                }
            if (s.charAt(i) == 'B')
                if (s.charAt(i+1) == 'A') {
                    idxBA[countBA%2] = i;
                    countBA += 1;
                }
        }
        if (countAB > 0 && countBA > 0 && countAB+countBA > 3)
            return true;
        else if (countAB > 0 && countBA > 0) {
            if (countAB+countBA == 2) { // 1 instance of each
                if (Math.abs(idxAB[0]-idxBA[0]) == 1) // overlap
                    return false;
                else
                    return true;
            }
            if (countAB == 1) {
                if (idxBA[1]-idxBA[0] == 2)
                    return false;
                else
                    return true;
            }
            else { // CountBA = 1
                if (idxAB[1]-idxAB[0] == 2)
                    return false;
                else
                    return true;
            }
        }
        else
            return false;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
