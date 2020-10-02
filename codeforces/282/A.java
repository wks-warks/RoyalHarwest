// Codeforces 282A
import java.util.Scanner;

public class CF282A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int operations = SC.nextInt();
        String[] operationsArr = new String[operations];
        for (int o = 0; o < operations; ++o)
            operationsArr[o] = SC.next();
        int finalValue = computeFinalValue(operationsArr); // Initial Value = 0
        System.out.println(finalValue);
    }

    // Computes and returns the final value of X
    static int computeFinalValue(String[] operationsArr) {
        int value = 0; // Initially X = 0
        for (int o = 0; o < operationsArr.length; ++o) {
            String operation = operationsArr[o];
            switch(operation.charAt(1)) {
                case '+': // +(+)X or X(+)+
                    value += 1;
                    break;
                case '-': // -(-)X or X(-)-
                    value -= 1;
                    break;
            }
        }
        return value;
    }
}