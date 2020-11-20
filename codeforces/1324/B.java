//Codeforces 1324B 
import java.util.Scanner;
import java.util.HashMap;

public class CF1324B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int elements = SC.nextInt();
            HashMap<Integer, Integer> occurrences = new HashMap<>();
            boolean palindromeFound = false;
            int prev = -1;
            for (int e = 0; e < elements; ++e) {
                int num = SC.nextInt();
                if (occurrences.containsKey(num)) {
                    int count = occurrences.get(num) + 1;
                    occurrences.put(num, count);
                    if (count == 2 && num != prev)
                        palindromeFound = true;
                    else if (count == 3)
                        palindromeFound = true;
                }
                else
                    occurrences.put(num, 1);
                prev = num;
            }
            out(palindromeFound);
        }
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
