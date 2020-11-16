//Codeforces 688B 
import java.util.Scanner;

public class CF688B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String numString = SC.next();
        String evenPalindrome = getEvenPalindrome(numString);
        System.out.println(evenPalindrome);
    }

    // Computes and returns num-th even palindrome
    static String getEvenPalindrome(String numString) {
        StringBuilder latterHalf = (new StringBuilder(numString)).reverse(); // Reverse of first half, which is numString itself
        String evenPalindrome = numString + latterHalf.toString();
        return evenPalindrome;
    }
}
