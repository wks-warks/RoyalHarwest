//Codeforces 1183A 
import java.util.Scanner;

public class CF1183A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int num = SC.nextInt();
        int digitSumDivBy4 = getDigitSumDivBy4(num);
        System.out.println(digitSumDivBy4);
    }

    // Computes and returns same/next number divisible by 4
    static int getDigitSumDivBy4(int num) {
        int digitSum = getDigitSum(num);
        if (digitSum % 4 == 0)
            return num;
        else   
            return getDigitSumDivBy4(num+1);
    }

    // Computes and returns sum of digits
    static int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
