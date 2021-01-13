//Codeforces 1398C 
import java.util.Scanner;
import java.util.TreeMap;

public class CF1398C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        while (tests-->0) {
            int arrLen = SC.nextInt();
            char[] inpArray = SC.next().toCharArray();
            int[] sumDiffs = getSumDiffs(inpArray);
            long answer = getAnswer(sumDiffs);
            System.out.println(answer);
        }
    }

    static long getAnswer(int[] sumDiffs) {
        TreeMap<Integer, Integer> diffCount = new TreeMap<Integer, Integer>();
        for (var element : sumDiffs) {
            if (diffCount.containsKey(element)) {
                diffCount.put(element, diffCount.get(element)+1);
            } else {
                diffCount.put(element, 1);
            }
        }
        
        long answer = 0;
        if (diffCount.containsKey(0)) answer += diffCount.get(0);
        for (var element : diffCount.entrySet()) {
            int key = element.getKey();
            long count = element.getValue();
            answer += count*(count-1)/2;
        }
        return answer;
    }

    static int[] getSumDiffs(char[] inpArray) {
        int[] sumDiffs = new int[inpArray.length];
        sumDiffs[0] = inpArray[0]-'1';
        for (int i = 1; i < inpArray.length; ++i) {
            sumDiffs[i] = sumDiffs[i-1] + inpArray[i]-'1';
        }
        return sumDiffs;
    }
}
