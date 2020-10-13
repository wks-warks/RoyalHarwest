// Codeforces 489C
import java.util.Scanner;

public class CF489C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numLen = SC.nextInt(); // Length of number excluding leading zeroes
        int numSum = SC.nextInt(); // Sum of individual digits of the number
        String minNum = getMinNum(numLen, numSum);
        String maxNum = getMaxNum(numLen, numSum);
        System.out.println(minNum + " " + maxNum);
    }

    // Computes and returns minimum number satisfying given condition, in string format
    static String getMinNum(int numLen, int numSum) {
        if (numSum == 0) {
            if (numLen != 1)
                return "-1";
            else
                return "0";
        }
        else if (numSum > 0 && numSum <= numLen*9) {
            String initialNum = getInitialNum(numLen);
            StringBuilder num = new StringBuilder();
            int presentSum = 1;
            for (int i = numLen-1; i >= 0; --i) { // Moving from LSB to MSB
                char digit = initialNum.charAt(i);
                int canAdd = 57 - digit; // Work in ASCII; Addition that can be made to given digit
                int feasibleToAdd = Math.min(numSum-presentSum, canAdd); // Addition limit that also considers sum constraint
                presentSum += feasibleToAdd;
                num.insert(0, (char)(digit+feasibleToAdd)); // Adding to front
            }
            return num.toString();
        }
        else
            return "-1";
    }

    static String getMaxNum(int numLen, int numSum) {
        if (numSum == 0) {
            if (numLen != 1)
                return "-1";
            else
                return "0";
        }
        else if (numSum <= numLen*9) {
            String initialNum = getInitialNum(numLen);
            StringBuilder num = new StringBuilder();
            int presentSum = 1;
            for (int i = 0; i < numLen; ++i) { // Moving from MSB to LSB
                char digit = initialNum.charAt(i);
                int canAdd = 57 - digit; // Addition that can be made to given digit
                int feasibleToAdd = Math.min(numSum-presentSum, canAdd); // Addition limit while also considering sum constrint
                presentSum += feasibleToAdd;
                num.append((char)(digit+feasibleToAdd)); // Adding to end
            }
            return num.toString();
        }
        else
            return "-1";
    }

    // Computes and returns minimum number of given length
    static String getInitialNum(int numLen) {
        StringBuilder num = new StringBuilder("1");
        for (int i = 1; i < numLen; ++i)
            num.append('0');
        return num.toString();
    }
}