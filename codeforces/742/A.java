//Codeforces 742A 
import java.util.Scanner;

public class CF742A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int pow = SC.nextInt();
        int lastDig = getLastDig(pow);
        System.out.println(lastDig);
    }

    // Computes and returns last digit of exp(1378, n)
    static int getLastDig(int pow) {
        if (pow == 0)
            return 1;
        switch (pow % 4) {
            case 0: return 6;
            case 1: return 8;
            case 2: return 4;
            case 3: return 2;
        }
        return -1; // Should not be executed
    }
}


/*
    Last digit concerns ones place so ignore greater values
    we are concerned with the last digit of exp(8, n)
    trend->
    n=0 lastDig = 1 // Special case
    
    n=1 lastDig = 8
    n=2 lastDig = 4
    n=3 lastDig = 2
    n=4 lastDig = 6
    n=5 lastDig = 8 -> Repetition
    lastDig depends on n%4
*/