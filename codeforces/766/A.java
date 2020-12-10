//Codeforces 766A 
import java.util.Scanner;

public class CF766A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String a = SC.next();
        String b = SC.next();
        System.out.println(a.equals(b) ? -1 : Math.max(a.length(), b.length()));
    }
}
