//Codeforces 1372B 
import java.util.Scanner;
 
public class CF1372B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int num = SC.nextInt();
            int factor = 1;
            for (int i = 2; i * i <= num; ++i)
                if (num % i == 0) {
                    factor = i;
                    break;
                }
            if (factor > 1)
                System.out.println(num / factor + " " + num / factor * (factor-1));
            else
                System.out.println(1 + " " + (num - 1));
        }        
    }
}