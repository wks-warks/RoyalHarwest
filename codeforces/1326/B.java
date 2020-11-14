//Codeforces 1326B 
import java.util.Scanner;

public class CF1326B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int arraySize = SC.nextInt();
        int[] differenceArray = new int[arraySize];
        for (int i = 0; i < arraySize; ++i)
            differenceArray[i] = SC.nextInt();
        int[] originalArray = getOriginalArray(differenceArray);
        out(originalArray);
    }

    // Computes and returns original array from the differenceArray
    static int[] getOriginalArray(int[] differenceArray) {
        int[] originalArray = new int[differenceArray.length];
        int largest = 0;
        for (int i = 0; i < originalArray.length; ++i) {
            originalArray[i] = largest + differenceArray[i];
            largest += Math.max(0, differenceArray[i]);
        }
        return originalArray;
    }

    // Prints out the array passed
    static void out(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i)
            sb.append(array[i] + " ");
        System.out.println(sb.toString());
    }
}
