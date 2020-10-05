// Codeforces 158B
import java.util.Scanner;

public class CF158B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numGroups = SC.nextInt(); // Number of groups
        int[] groupSize = new int[numGroups];
        for (int g = 0; g < numGroups; ++g)
            groupSize[g] = SC.nextInt();
        int taxisRequired = computeTaxisRequired(groupSize);
        System.out.println(taxisRequired);
    }

    // Computes minimum number of taxis required
    static int computeTaxisRequired(int[] groupSize) {
        int[] numBySize = new int[4]; // Number of groups of size 1-4 (idx 0-3)
        for (int g = 0; g < groupSize.length; ++g) {
            int size = groupSize[g];
            numBySize[size-1] += 1; // Corresponing idx = size - 1
        }
        /*  When accommodating students, we follow the greedy approach
        *   We first try to accommodate the largest groups as they would need a car to themselves anyway
        *   Accommodating them first allows us to potentially accommodate some groups of smaller size without wastage 
        */  
        int taxisRequired = 0;
        taxisRequired += numBySize[3]; // Number of groups of size 4
        taxisRequired += numBySize[2]; // Groups of size 3 also require a taxi each
        numBySize[0] = Math.max(0, numBySize[0] - numBySize[2]); // Accommodating groups of size 1 with those of size 3 if possible
        
        // Note that thus far all taxis have either been full or have accommodated as many students as possible
        // There is no room in them to accommodate students who have not been accommodated yet
        taxisRequired += numBySize[1] / 2; // Two groups of size 2 can fit in one taxi
        numBySize[1] %= 2;
        int remainingStudents = numBySize[0] + numBySize[1]*2; // In case there was a lone group of size 2 remaining, that will also be included
        taxisRequired += leastIntegerGreater(remainingStudents, 4); // Adds least integer greater than or equal to (doubl)remainingStudents/4
        return taxisRequired;
    }

    // Returns least integer greater than (double) numerator/denominator
    static int leastIntegerGreater(int numerator, int denominator) {
        // Assuming non-negative numerator and positive denominator-
        return (numerator + denominator - 1) / denominator;
    }
}