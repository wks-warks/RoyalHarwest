//Codeforces 1182A 
import java.util.Scanner;

public class CF1182A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        System.out.println(getAns(n));
    }

    // Returns answer value
    static int getAns(int n) {
        if (n % 2 == 1)
            return 0;
        
        return 1 << (n/2);
    }
}
