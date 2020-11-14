//Codeforces 894A 
import java.util.Scanner;

public class CF894A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String str = SC.next();
        int QAQs = countQAQs(str);
        System.out.println(QAQs);
    }

    // Computes and returns number of QAQs in the string
    static int countQAQs(String str) {
        int[] Qs = new int[str.length()]; // Stores number of Qs upto an index
        int Qfound = 0;
        for (int i = 0; i < str.length(); ++i) {
            Qs[i] = Qfound;
            if (str.charAt(i) == 'Q')
                Qfound += 1;
        }
        // Qfound = total Qs now.
        // For each 'A', we shall now see how many Qs exist before and after it and add to QAQ-count accordingly
        int QAQs = 0;
        for (int i = 0; i < str.length(); ++i)
            if (str.charAt(i) == 'A')
                QAQs += (Qs[i] * (Qfound-Qs[i]));
        return QAQs;
    }
}
