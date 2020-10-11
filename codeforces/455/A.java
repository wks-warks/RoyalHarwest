// Codeforces 455A
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF455A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        Map <Integer, Long> elementSum = new TreeMap<>(); // Maps element against total sum obtained by summing all occurrences of the element
        int numbers = SC.nextInt();
        // Filling all key-value pairs in elementSum
        for (int i = 0; i < numbers; ++i) {
            int element = SC.nextInt();
            if (elementSum.containsKey(element))
                elementSum.put(element, (long)elementSum.get(element)+element); // Putting value = presentValue + element
            else
                elementSum.put(element, (long)element);
        }
        // In order to maximize points we will have to take all instances of any element if we take any single instance 
        // Therefore we filled the Map elementSum with element-Sum values where sum is the total sum of all the instances of that element
        long maxPoints = computeMaxPoints(elementSum);
        System.out.println(maxPoints);
    }

    // Computes and returns maximum possible sum obtainable
    static long computeMaxPoints(Map<Integer, Long> elementSum) {
        int prevKey = -1;
        long pointsTakingLast = 0;
        long pointsSkippingLast = 0;
        for (Map.Entry<Integer, Long> keyVal : elementSum.entrySet()) {
            int key = keyVal.getKey();
            long value = keyVal.getValue();
            long pointsSkippingThis, pointsTakingThis;
            pointsSkippingThis = Math.max(pointsSkippingLast, pointsTakingLast);
            if (key == prevKey + 1) // In this case, in order to take instances of this number, we must ensure that we haven't picked any instance of the previous number
                pointsTakingThis = pointsSkippingLast + value;
            else
                pointsTakingThis = value + pointsSkippingThis; // value + max of previouslyPossible points
            pointsSkippingLast = pointsSkippingThis;
            pointsTakingLast = pointsTakingThis;
            prevKey = key;
        }
        long maxPoints = Math.max(pointsSkippingLast, pointsTakingLast);
        return maxPoints;
    }
}