//Codeforces 977C 
import java.util.Scanner;
import java.util.Arrays;

public class CF977C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int k = SC.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; ++i)
            a[i] = SC.nextInt();
        int greaterThanK = findGreaterThanK(a, k);
        System.out.println(greaterThanK);
    }

    // Computes and returns the answer
    static int findGreaterThanK(Integer[] a, int k) {
        Arrays.sort(a);
        if (k == a.length)
            return (int) 1e9;
        else if (k == 0) {
            if (a[0].equals(1))
                return -1;
            else
                return 1;
        }
        else if (a[k].equals(a[k-1]))
            return -1;
        else
            return a[k-1];
    }
}
