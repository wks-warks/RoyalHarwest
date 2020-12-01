//Codeforces 513A 
import java.util.Scanner;

public class CF513A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int ballCount1 = SC.nextInt();
        int ballCount2 = SC.nextInt();
        int garbage = SC.nextInt();
        garbage = SC.nextInt();
        System.out.println((ballCount1 > ballCount2) ? "First" : "Second");
    }
}