//Codeforces 1369B 
import java.util.Scanner;

public class CF1369B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int strLen = SC.nextInt();
            String binString = SC.next();
            String cleanString = getCleanString(binString);
            solution.append(cleanString + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns clean string from given string
    static String getCleanString(String binString) {
        int firstOne = -1;
        for (int i = 0; i < binString.length(); ++i) {
            if (binString.charAt(i) == '1') {
                firstOne = i;
                break;
            }
        }

        int lastZero = -1;
        for (int i = binString.length()-1; i > -1; --i) {
            if (binString.charAt(i) == '0') {
                lastZero = i;
                break;
            }
        }

        if (lastZero == -1 || firstOne == -1 || firstOne > lastZero)
            return binString;
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= firstOne; ++i)
                sb.append('0');
            for (int i = lastZero+1; i < binString.length(); ++i)
                sb.append('1');
            return sb.toString();
        }
    }
}

/*
1
8
11001101
*/