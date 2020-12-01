//Codeforces 1005A 
import java.util.Scanner;
import java.util.ArrayList;

public class CF1005A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int totalCounts = SC.nextInt();
        int[] numbersPronounced = new int[totalCounts];
        for (int c = 0; c < totalCounts; ++c) {
            numbersPronounced[c] = SC.nextInt();
        }
        ArrayList<Integer> staircases = getStaircases(numbersPronounced);
        print(staircases);
    }

    // Computes and returns length of the staircases in an arraylist
    static ArrayList<Integer> getStaircases(int[] numbersPronounced) {
        ArrayList<Integer> staircases = new ArrayList<>();
        for (int count = 1; count < numbersPronounced.length; ++count) {
            if (numbersPronounced[count] == 1) {
                staircases.add(numbersPronounced[count-1]);
            }
        }
        staircases.add(numbersPronounced[numbersPronounced.length-1]);
    
        return staircases;
    }

    // Prints out the arraylist length and values
    static void print(ArrayList<Integer> arrayList) {
        System.out.println(arrayList.size());
        for (int num : arrayList) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
