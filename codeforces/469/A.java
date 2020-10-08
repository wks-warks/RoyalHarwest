// Codeforces 469A
import java.util.Scanner;

public class CF469A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int levels = SC.nextInt();
        boolean[] canClear = new boolean[levels];
        int passX = SC.nextInt(); // Levels passed by X
        for (int p = 0; p < passX; ++p) {
            int levelPassed = SC.nextInt();
            canClear[levelPassed-1] = true;
        }
        int passY = SC.nextInt(); // Levels passed by Y
        for (int p = 0; p < passY; ++p) {
            int levelPassed = SC.nextInt();
            canClear[levelPassed-1] = true;
        }
        boolean canClearAll = checkAll(canClear);
        out(canClearAll);
    }

    // Checks and returns boolean whether all levels can be cleared
    static boolean checkAll(boolean[] canClear) {
        for (int i = 0; i < canClear.length; ++i)
            if (!canClear[i])
                return false;
        return true; // Can clear all levels
    }

    // Prints output corresponding to case
    static void out(boolean canClearAll) {
        if (canClearAll)
            System.out.println("I become the guy.");
        else
            System.out.println("Oh, my keyboard!");
    }
}