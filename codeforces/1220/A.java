//Codeforces 1220A 
import java.util.Scanner;

public class CF1220A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int characters = SC.nextInt();
        String cards = SC.next();
        String binaryNum = getNum(cards);
        System.out.println(binaryNum);
    }

    // Computes and returns the binary number
    static String getNum(String cards) {
        int zeroes = 0;
        int ones = 0;
        for (int i = 0; i < cards.length(); ++i)
            if (cards.charAt(i) == 'z')
                zeroes += 1;
        ones = (cards.length() - 4*zeroes) / 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ones; ++i)
            sb.append("1 ");
        for (int i = 0; i < zeroes; ++i)
            sb.append("0 ");
        return sb.toString();
    }
}
