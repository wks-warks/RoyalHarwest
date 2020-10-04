// Codeforces 133A
import java.util.Scanner;

public class CF133A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String program = SC.next();
        boolean printsSomething = checkIfPrints(program);
        out(printsSomething);
    }

    // Checks if the given program gives some textual output
    static boolean checkIfPrints(String program) {
        for (int i = 0; i < program.length(); ++i) {
            char command = program.charAt(i);
            switch(command) {
                case 'H':
                case 'Q':
                case '9':
                    return true;
            }
        }
        return false; // None of the commands prints anything
    }

    // Prints output corresponding to the condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}