//Codeforces 1006A 
import java.util.Scanner;

public class CF1006A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int arrayLength = SC.nextInt();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            array[i] = SC.nextInt();
        }
        transform(array);
        print(array);
    }

    // Transforms the array to Mishka's liking
    static void transform(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = (array[i]+1)/2 *2 -1;
        }
    }

    // Prints out all elements of the array
    static void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
