//Codeforces 1450 A 
import java.util.Scanner;

public class CFA {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int aLen = SC.nextInt();
            String a = SC.next();
            String b = getB(a);
            System.out.println(b);
        }
    }

    // Computes and returns b
    static String getB(String a) {
        int countU = 0;
        for (char c : a.toCharArray()) {
            if (c == 'u')
                countU += 1;
        }

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < countU; ++i)
            b.append('u');
        
        for (char c : a.toCharArray()) {
            if (c != 'u')
                b.append(c);
        }

        return b.toString();
    }
}
