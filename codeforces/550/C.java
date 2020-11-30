//Codeforces 550C 
import java.util.Scanner;

public class CF550C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String numString = SC.next();
        String result = getResult(numString);
        System.out.println(result);
    }

    // Computes and returns the result based on the number passed
    static String getResult(String numString) {
        for (int i = 0; i  < numString.length(); ++i)
            switch(numString.charAt(i)) {
                case '0':
                case '8':
                    return "YES\n" + numString.charAt(i);
            }

        boolean checkedTwo, checkedFour, checkedSix;
        checkedTwo = checkedFour = checkedSix = false;
        for (int i = numString.length() - 1; i >= 0; --i)
            switch (numString.charAt(i)) {
                case '2':
                    if (checkedTwo)
                        continue;
                    for (int j = i-1; j >= 0; --j)
                        switch(numString.charAt(j)) {
                            case '3':
                            case '7':
                                return "YES\n" + numString.charAt(j) + numString.charAt(i);
                            case '1':
                            case '5':
                            case '9':
                                for (int k = j-1; k >= 0; --k)
                                    if (numString.charAt(k) % 2 == 1) // num - '0' = -48, makes no difference to parity
                                        return "YES\n" + numString.charAt(k) + numString.charAt(j) + numString.charAt(i);
                        }
                    checkedTwo = true;
                    break;
                case '4':
                    if (checkedFour)
                        continue;
                    for (int j = i-1; j >= 0; --j)
                        switch(numString.charAt(j)) {
                            case '2':
                            case '6':
                                return "YES\n" + numString.charAt(j) + numString.charAt(i);
                            case '4':
                                for (int k = j-1; k >= 0; --k)
                                    if (numString.charAt(k) % 2 == 1)
                                        return "YES\n" + numString.charAt(k) + numString.charAt(j) + numString.charAt(i);
                        }
                    checkedFour = true;
                    break;
                case '6':
                    if (checkedSix)
                        continue;
                    for (int j = i-1; j >= 0; --j)
                        switch(numString.charAt(j)) {
                            case '1':
                            case '5':
                            case '9':
                                return "YES\n" + numString.charAt(j) + numString.charAt(i);
                            case '3':
                            case '7':
                                for (int k = j-1; k >= 0; --k)
                                    if (numString.charAt(k) % 2 == 1)
                                        return "YES\n" + numString.charAt(k) + numString.charAt(j) + numString.charAt(i);
                        }
                    checkedSix = true;
            }
        return "NO";
    }
}
