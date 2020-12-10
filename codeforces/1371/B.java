//Codeforces 1371B 
import java.util.Scanner;

public class CF1371B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int r = SC.nextInt();
            long answer = getAnswer(n, r);
            System.out.println(answer);
        }
    }

    // Computes and returns the answer to the given case
    static long getAnswer(int n, int r) {
        int smaller = Math.min(n-1, r);
        long answer = (long) smaller * (smaller+1) / 2;
        if (r >= n)
            answer += 1;
        return answer;
    }
}
