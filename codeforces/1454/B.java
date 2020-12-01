//Codeforces 1454B 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF1454B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int participants = SC.nextInt();
            int[] chosenNumbers = new int[participants];
            for (int p = 0; p < participants; ++p) {
                chosenNumbers[p] = SC.nextInt();
            }
            int winnerIdx = getWinnerIdx(chosenNumbers);
            System.out.println(winnerIdx);
        }
    }

    // Computes and returns idx of winner, returns -1 if no winner found
    static int getWinnerIdx(int[] chosenNumbers) {
        TreeMap<Integer, Integer> bidIdx = new TreeMap<Integer, Integer>();
        for (int i = 0; i < chosenNumbers.length; ++i) {
            if (bidIdx.containsKey(chosenNumbers[i])) {
                bidIdx.put(chosenNumbers[i], -1);
            }
            else {
                bidIdx.put(chosenNumbers[i], i+1);
            }
        }

        int winnerIdx = -1;
        for (Map.Entry<Integer, Integer> bid : bidIdx.entrySet()) {
            int idx = bid.getValue();
            if (idx != -1) {
                winnerIdx = idx;
                break;
            }
        }
        return winnerIdx;
    }
}
