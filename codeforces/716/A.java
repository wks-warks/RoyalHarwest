//Codeforces 716A 
import java.util.Scanner;

public class CF716A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int wordsTyped = SC.nextInt();
        int screenTimeOut = SC.nextInt();
        int[] typingTimes = new int[wordsTyped];
        for (int w = 0; w < wordsTyped; ++w)
            typingTimes[w] = SC.nextInt();
        int wordsAtEnd = countWordsAtEnd(wordsTyped, screenTimeOut, typingTimes);
        System.out.println(wordsAtEnd);
    }

    // Computes and returns words staying on the screen at the end of the typing spree
    static int countWordsAtEnd(int wordsTyped, int screenTimeOut, int[] typingTimes) {
        int prevTime = 0;
        int onScreen = 0;
        for (int w = 0; w < wordsTyped; ++w) {
            int presTime = typingTimes[w];
            if (presTime - prevTime > screenTimeOut)
                onScreen = 1;
            else
                onScreen += 1;
            prevTime = presTime;
        }
        return onScreen;
    }
}
