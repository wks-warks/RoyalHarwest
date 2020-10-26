//Codeforces 1339A 
import java.util.Scanner;

public class CF1339A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numTests = SC.nextInt();
        int[] tests = new int[numTests];
        for (int t = 0; t < numTests; ++t)
            tests[t] = SC.nextInt();
        String response = getResponse(tests);
        System.out.print(response);
    }

    // Computes and returns response to test cases
    static String getResponse(int[] tests) {
        StringBuilder response = new StringBuilder();
        for (int t = 0; t < tests.length; ++t)
            response.append(tests[t] + "\n");
        return response.toString();
    }
}
