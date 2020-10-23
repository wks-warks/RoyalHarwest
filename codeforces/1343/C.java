// Codeforces 1343C
import java.util.Scanner;
import java.util.Vector;

public class CF1343C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            long solutionSum = getSolutionSum(arr);
            System.out.println(solutionSum);
        }
    }

    // Computes and returns sum of longest alternating sequence with maximum sum
    static long getSolutionSum(int[] arr) {
        Vector<Integer> negFirst = new Vector<>();
        Vector<Integer> posFirst = new Vector<>();
        boolean prevPosNF = false; // Notes whether the last addition to negFirst was posititve or not 
        boolean prevPosPF = true; // Notes whether the last addition to posFirst was positive or not

        int addToNF = Integer.MIN_VALUE; // This won't be added
        int addToPF = Integer.MIN_VALUE; // This won't be added

        for (int i = 0; i < arr.length; ++i) {
            boolean isPos = arr[i] > 0;
            // Working on negFirst vector
            if (prevPosNF) {
                if (isPos) {
                    addToNF = Math.max(arr[i], addToNF);
                }
                else {
                    negFirst.add(addToNF);
                    addToNF = arr[i];
                    prevPosNF = false;
                }
            }
            else {
                if (isPos) {
                    if (addToNF != Integer.MIN_VALUE) // Initial value
                        negFirst.add(addToNF);
                    addToNF = arr[i];
                    prevPosNF = true;
                }
                else {
                    addToNF = Math.max(arr[i], addToNF);
                }
            }
            // Working on posFirst vector
            if (prevPosPF) {
                if (isPos) {
                    addToPF = Math.max(arr[i], addToPF);
                }
                else {
                    if (addToPF != Integer.MIN_VALUE)
                        posFirst.add(addToPF);
                    addToPF = arr[i];
                    prevPosPF = false;
                }
            }
            else {
                if (isPos) {
                    posFirst.add(addToPF);
                    addToPF = arr[i];
                    prevPosPF = true;
                }
                else {
                    addToPF = Math.max(arr[i], addToPF);
                }
            }
        }
        // Adding last element if not added already
        if (negFirst.size() == 0 && addToNF != Integer.MIN_VALUE)
            negFirst.add(addToNF);
        else {
            int lastAddedNF = negFirst.get(negFirst.size()-1);
            if (lastAddedNF != addToNF)
                negFirst.add(addToNF);
        }
        if (posFirst.size() == 0 && addToPF != Integer.MIN_VALUE)
            posFirst.add(addToPF);
        else {
            int lastAddedPF = posFirst.get(posFirst.size()-1);
            if (lastAddedPF != addToPF)
                posFirst.add(addToPF);
        }
        
        if (negFirst.size() > posFirst.size())
            return vectorSum(negFirst);
        else if (posFirst.size() > negFirst.size())
            return vectorSum(posFirst);
        else // Same size
            return Math.max(vectorSum(negFirst), vectorSum(posFirst));
    }

    // Returns sum of elements of vector
    static long vectorSum(Vector<Integer> v) {
        long sum = 0;
        for (int num : v)
            sum += num;
        return sum;
    }
}