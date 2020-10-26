//Codeforces 368B 
import java.util.Scanner;
import java.util.HashMap;

public class CF368B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int arrayLen = SC.nextInt();
        int numQueries = SC.nextInt();
        int[] array = new int[arrayLen];
        for (int i = 0; i < arrayLen; ++i)
            array[i] = SC.nextInt();
        int[] queries = new int[numQueries];
        for (int i = 0; i < numQueries; ++i)
            queries[i] = SC.nextInt();
        String response = resolveQueries(array, queries);
        System.out.print(response);
    }

    // Computes and returns string with responses to queries
    static String resolveQueries(int[] array, int[] queries) {
        int[] answers = new int[array.length];
        HashMap<Integer, Boolean> occurrence = new HashMap<>();
        for (int i = array.length-1; i >= 0; --i) {
            occurrence.put(array[i], true);
            answers[i] = occurrence.size();
        }
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < queries.length; ++i)
            response.append(answers[queries[i]-1] + "\n");
        return response.toString();
    }
}
