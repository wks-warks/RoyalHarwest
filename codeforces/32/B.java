//Codeforces 32B 
import java.util.Scanner;

public class CF32B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String telegraphed = SC.next();
        String numeric = getNumeric(telegraphed);
        System.out.println(numeric);
    }

    // Computes and returns numeric form from telegraphed form
    static String getNumeric(String telegraphed) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < telegraphed.length(); ++i)
            if (telegraphed.charAt(i) == '.')
                sb.append('0');
            else {
                if (telegraphed.charAt(i+1) == '.')
                    sb.append('1');
                else
                    sb.append('2');
                i += 1;
            }
        return sb.toString();
    }
}
