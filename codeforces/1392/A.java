//Codeforces 1392A 
import java.util.Scanner;

public class CF1392A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int passLength = SC.nextInt();
            int[] passWord = new int[passLength];
            for (int i = 0; i < passLength; ++i)
                passWord[i] = SC.nextInt();
            int minLength = computeMinLength(passWord);
            System.out.println(minLength);
        }
    }

    // Computes and returns minimum length obtainaible
    static int computeMinLength(int[] passWord) {
        boolean allSame = true;
        for (int i = 1; i < passWord.length; ++i)
            if (passWord[i] != passWord[i-1]) {
                allSame = false;
                break;
            }
        
        return allSame? passWord.length : 1;
    }
}
