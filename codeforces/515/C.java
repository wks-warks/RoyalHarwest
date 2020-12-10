//Codeforces 515C 
import java.util.Scanner;


public class CF515C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int digits = SC.nextInt();
        String numString = SC.next();
        String maximalNumber = computeMaximalNumber(numString);
        print(maximalNumber);
    }

    // Computes and returns maximal number-string satisfying given conditions
    static String computeMaximalNumber(String numString) {
        int[] resultOccurrences = new int[10];
        for (char c : numString.toCharArray()) {
            switch (c) {
                case '0':
                case '1':
                    break;
                case '2':
                    resultOccurrences[2] += 1;
                    break;
                case '3':
                    resultOccurrences[3] += 1;
                    break;
                case '4':
                    resultOccurrences[3] += 1;
                    resultOccurrences[2] += 2;
                    break;
                case '5':
                    resultOccurrences[5] += 1;
                    break;
                case '6':
                    resultOccurrences[5] += 1;
                    resultOccurrences[3] += 1;
                    break;
                case '7':
                    resultOccurrences[7] += 1;
                    break;
                case '8':
                    resultOccurrences[7] += 1;
                    resultOccurrences[2] += 3;
                    break;
                default:
                    resultOccurrences[7] += 1;
                    resultOccurrences[2] += 1;
                    resultOccurrences[3] += 2;
            }
        }
        StringBuilder maxNumSB = new StringBuilder();
        for (int i = 9; i >= 0; --i)
            for (int j = 0; j < resultOccurrences[i]; ++j)
                maxNumSB.append((char) ('0'+i));
        return maxNumSB.toString();
    }

    // Prints out the maximal number in the desired format
    static void print(String maximalNumber) {
        System.out.println(maximalNumber);
    }
}
