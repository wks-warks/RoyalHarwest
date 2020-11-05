//Codeforces 1398B 
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

public class CF1398B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String binString = SC.next();
            int aliceScore = getAliceScore(binString);
            System.out.println(aliceScore);
        }
    }

    // Compute's and returns Alice's score if the game is played optimally
    static int getAliceScore(String binString) {
        Vector<Integer> oneStrips = new Vector<>(); // Length of contiguous strips of 1s
        boolean prevZero = true;
        oneStrips.add(0);
        for (int i = 0; i < binString.length(); ++i)
            if (binString.charAt(i) == '1') {
                oneStrips.set(oneStrips.size()-1, oneStrips.get(oneStrips.size()-1) + 1);
                prevZero = false;
            }
            else if (!prevZero) {
                prevZero = true;
                oneStrips.add(0);
            }
        
        Collections.sort(oneStrips, Collections.reverseOrder());
        int aliceScore = 0;
        for (int i = 0; i < oneStrips.size(); i+= 2)
            aliceScore += oneStrips.get(i);
        return aliceScore;
    }
}
