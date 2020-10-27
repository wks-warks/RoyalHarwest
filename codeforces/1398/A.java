//Codeforces 1398A 
import java.util.Scanner;

public class CF1398A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            IdxTriplet solution = getSolutionTriplet(arr);
            out(solution);
        }
    }

    // Computes and returns triplet of solution indices
    static IdxTriplet getSolutionTriplet(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE; // Smallest
        int min2 = Integer.MAX_VALUE; // Second Smallest
        int maxIdx, min1Idx, min2Idx; // 1-based indices of their instances
        maxIdx = min1Idx = min2Idx = -1;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i+1;
            }
            if (arr[i] < min2) {
                min2 = arr[i];
                min2Idx = i+1;
                if (min2 < min1) {
                    int temp = min1;
                    min1 = min2;
                    min2 = temp;
                    temp = min1Idx;
                    min1Idx = min2Idx;
                    min2Idx = temp;
                }
            }
        }
        if (min1 + min2 <= max)
            return new IdxTriplet(min1Idx, min2Idx, maxIdx);
        else
            return new IdxTriplet();
    }

    // Prints output corresponding to solution
    static void out(IdxTriplet solution) {
        if (solution.getFirst() == -1)
            System.out.println(-1);
        else {
            int idx1 = solution.getFirst();
            int idx2 = solution.getSecond();
            int idx3 = solution.getThird();
            System.out.println(idx1 + " " + idx2 + " " + idx3);
        }
    }
}

// Holds triplet of indices composing the solution
class IdxTriplet {
    private int idx1 = -1;
    private int idx2 = -1;
    private int idx3 = -1;
    public IdxTriplet() {
        
    }
    public IdxTriplet(int idx1, int idx2, int idx3) {
        this.idx1 = idx1;
        this.idx2 = idx2;
        this.idx3 = idx3;
    }

    // Getter functions
    public int getFirst() {
        return idx1;
    }
    public int getSecond() {
        return idx2;
    }
    public int getThird() {
        return idx3;
    }
}