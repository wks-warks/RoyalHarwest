//Codeforces 919B 
import java.util.Scanner;

public class CF919B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int k = SC.nextInt();
        
        int num = 0;
        int perfectNums = 0;
        while (perfectNums < k) {
            num += 1;
            if (isPerfect(10, num))
                perfectNums += 1;    
        }

        System.out.println(num);
    }

    static boolean isPerfect(int sum, int num) {
        if ((sum > 0 && num == 0) || sum < 0)
            return false;
        else if (num == 0 && sum == 0)
            return true;
        else
            return isPerfect(sum - num%10, num/10);
    }
}
