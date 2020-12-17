//Codeforces 1223B 
import java.util.Scanner;
import java.util.TreeSet;
 
public class CF1223B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int  t = 0; t < tests; ++t) {
            String first = SC.next();
            String second = SC.next();
            TreeSet<Character> firstTreeSet = new TreeSet<>();
            for (char c : first.toCharArray())
                firstTreeSet.add(c);
            TreeSet<Character> secondTreeSet = new TreeSet<>();
            for (char c : second.toCharArray())
                secondTreeSet.add(c);
            TreeSet<Character> intersection = new TreeSet<>(firstTreeSet);
            intersection.retainAll(secondTreeSet);
            System.out.println(intersection.size() > 0 ? "Yes" : "No");
        }
    }
}