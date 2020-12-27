//Codeforces 1293B 
import java.util.Scanner;

public class CF1293B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int opponents = SC.nextInt();
        double answer = 0;
        while(opponents>0) {
            answer += 1.0 / opponents--;
        }
        System.out.println(answer);
    }
}
