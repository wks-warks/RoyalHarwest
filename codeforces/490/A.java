// Codeforces 490A
import java.util.Scanner;
import java.util.Vector;

public class CF490A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int children = SC.nextInt(); // Number of children
        Vector<Integer> skill1Idx = new Vector<>(); // 1-idx of children with skill1
        Vector<Integer> skill2Idx = new Vector<>(); // 1-idx of children with skill2
        Vector<Integer> skill3Idx = new Vector<>(); // 1-idx of children with skill3
        for (int c = 1; c <= children; ++c) {
            int skill = SC.nextInt();
            if (skill == 1)
                skill1Idx.add(c);
            else if (skill == 2)
                skill2Idx.add(c);
            else
                skill3Idx.add(c);
        }
        out(skill1Idx, skill2Idx, skill3Idx);
    }

    // Presents output based on student distribution vector passed
    static void out(Vector<Integer> skill1Idx, Vector<Integer> skill2Idx, Vector<Integer> skill3Idx) {
        int possibleGroups = Math.min(skill1Idx.size(), Math.min(skill2Idx.size(), skill3Idx.size())); // Number of groups possible
        System.out.println(possibleGroups);
        for (int i = 0; i  < possibleGroups; ++i) {
            // Creating and printing group details
            int idx1 = skill1Idx.get(i);
            int idx2 = skill2Idx.get(i);
            int idx3 = skill3Idx.get(i);
            System.out.println(idx1 + " " + idx2 + " " + idx3);
        }
    }
}