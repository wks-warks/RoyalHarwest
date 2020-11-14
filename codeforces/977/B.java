//Codeforces 977B 
import java.util.Scanner;

public class CF977B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int strLen = SC.nextInt();
        String str = SC.next();
        String frequentPair = getFrequentPair(strLen, str);
        System.out.println(frequentPair);
    }

    // Finds and returns most frequent two-gram
    static String getFrequentPair(int strLen, String str) {
        int[][] twoGramCount = new int[26][26];
        int maxCount = 0;
        String frequentPair = "";
        for (int i = 1; i < strLen; ++i) {
            int idx1 = str.charAt(i-1) - 'A';
            int idx2 = str.charAt(i) - 'A';
            int count = (twoGramCount[idx1][idx2] += 1);
            if (count > maxCount) {
                maxCount = count;
                frequentPair = (char)('A' + idx1) + "" + (char) ('A' + idx2);
            }
        }
        return frequentPair;
    }
}
