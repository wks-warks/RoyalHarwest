//Codeforces 1409C 
import java.util.Scanner;

public class CF1409C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int x = SC.nextInt();
            int y = SC.nextInt();
            int[] solutionArr = getSolutionArr(arrLen, x, y);
            print(solutionArr);
        }
    }

    // Returns solution array
    static int[] getSolutionArr(int arrLen, int x, int y) {
        int diff = y - x;
        for (int i = arrLen - 1; i > 0; --i) {
            if ((y - x) % i == 0) {
                diff = (y-x) / i;
                break;
            }
        }

        int xPos = arrLen - 1 - (y-x) / diff;
        for (int i = xPos; i >= 0; --i) {
            if (x - i * diff > 0) {
                xPos = i;
                break;
            }
        }
        int yPos = xPos + (y-x) / diff;
        int[] solutionArr = new int[arrLen];
        for (int i = 0; i < arrLen; ++i) {
            solutionArr[i] = x - (xPos-i) * diff;
        }        
        return solutionArr;
    }

    // Prints solution array
    static void print(int[] solutionArr) {
        for (int i = 0; i < solutionArr.length; ++i)
            System.out.print(solutionArr[i] + " ");
        System.out.println();
    }
}
