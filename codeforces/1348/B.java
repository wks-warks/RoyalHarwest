//Codeforces 1348B 
import java.util.Scanner;
import java.util.TreeSet;

public class CF1348B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int numElements = SC.nextInt();
            int subArrayLength = SC.nextInt();
            int[] array = new int[numElements];
            for (int i = 0; i < numElements; ++i)
                array[i] = SC.nextInt();
            boolean canBeautify = checkIfBeautifiable(array, subArrayLength);
            if (canBeautify) {
                printBeautifulArray(array, subArrayLength);
            }
            else {
                System.out.println(-1);
            }
        }
    }

    // Checks and returns whether or not the array can be beautified
    static boolean checkIfBeautifiable(int[] array, int subArrayLength) {
        TreeSet<Integer> presentNos = new TreeSet<>();
        for (int i = 0; i < array.length; ++i) {
            presentNos.add(array[i]);
        }
        return subArrayLength >= presentNos.size();
    }

    // Prints beautiful array
    static void printBeautifulArray(int[] array, int subArrayLength) {
        TreeSet<Integer> nos = new TreeSet<>();
        for (int i = 0; i < array.length; ++i)
            nos.add(array[i]);
        
        int addMore = subArrayLength - nos.size();
        for (int i = 1; addMore > 0; ++i) {
            if (!nos.contains(i)) {
                nos.add(i);
                addMore -= 1;
            }
        }

        System.out.println(array.length * subArrayLength);
        for (int i = 0; i < array.length; ++i) {
            for (int n : nos)
                System.out.print(n + " ");
        }
        System.out.println();
    }
}
