// Codeforces 472A
import java.util.Scanner;

public class CF472A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int[] compositeAddends = getCompositeAddends(n); // Two composite addends of n
        out(compositeAddends);
    }

    // Gets composite addends for n
    static int[] getCompositeAddends(int n) {
        int[] addends = new int[2];
        if (n % 2 == 0) {
             addends[0] = 4;
             addends[1] = n-4;
        }
        else {
            addends[0] = 9;
            addends[1] = n-9;
        }
        return addends;
    }

    // Prints output corresponding to array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
    }
}