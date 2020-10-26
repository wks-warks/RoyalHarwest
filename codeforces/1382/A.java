//Codeforces 1382A 
import java.util.Scanner;
import java.util.HashMap;

public class CF1382A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arr1Len = SC.nextInt();
            int[] arr1 = new int[arr1Len];
            int arr2Len = SC.nextInt();
            int[] arr2 = new int[arr2Len];

            for (int i = 0; i < arr1Len; ++i)
                arr1[i] = SC.nextInt();
            for (int i = 0; i < arr2Len; ++i)
                arr2[i] = SC.nextInt();
            Response testResponse = findResponse(arr1, arr2);
            out(testResponse);
        }
    }

    // Finds and returns response for the given test
    static Response findResponse(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> arr1Elements = new HashMap<>();
        for (int i = 0; i < arr1.length; ++i)
            arr1Elements.put(arr1[i], true);

        for (int i = 0; i < arr2.length; ++i)
            if (arr1Elements.containsKey(arr2[i]))
                return new Response("YES", arr2[i]);
        return new Response("NO"); // NO match found
    }

    // Prints output corresponding to response
    static void out(Response testResponse) {
        String existence = testResponse.getExistence();
        System.out.println(existence);
        if (existence.equals("YES"))
            System.out.println(testResponse.getLength() + " " + testResponse.getElement());
    }
}

class Response {
    private String existence;
    private int subsequnceLength;
    private int element;
    public Response(String existence) {
        this.existence = existence;
    }
    public Response(String existence, int element) {
        this.existence = existence;
        subsequnceLength = 1;
        this.element = element;
    }
    // Returns whether or not answer exists ("YES" or "NO")
    public String getExistence() {
        return existence;
    }
    // Returns subsequence length (always 1 if answer exists)
    public int getLength() {
        return subsequnceLength;
    }
    // Returns element of subsequence
    public int getElement() {
        return element;
    }
}