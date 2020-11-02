//Codeforces 1352B 
import java.util.Scanner;

public class CF1352B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int number = SC.nextInt();
            int parts = SC.nextInt(); // Number of elements the sum of which is equal to number
            Response testResponse = getResponse(number, parts);
            out(testResponse);
        }
    }

    // Computes and returns test response
    static Response getResponse(int number , int parts) {
        // Case 1: Parts->Even
        if (parts % 2 == 0) {
            if (number % 2 != 0 || number < parts)
                return new Response(false);
            int[] representation = new int[parts];
            for (int r = 1; r < representation.length; ++r)
                representation[r] = 1;
            representation[0] = number - parts + 1;
            return new Response(true, representation);
        }
        // Case 2: Parts->Odd
        else {
            // Subcase a: Number->Even
            if (number % 2 == 0) {
                if (number < 2 * parts)
                    return new Response(false);
                int[] representation = new int[parts];
                for (int r = 1; r < representation.length; ++r)
                    representation[r] = 2;
                representation[0] = number - 2*(parts-1);
                return new Response(true, representation);
            }
            // Subcase b: Number->Odd
            else {
                if (number < parts)
                    return new Response(false);
                int[] representation = new int[parts];
                for (int r = 1; r < representation.length; ++r)
                    representation[r] = 1;
                representation[0] = number - (parts-1);
                return new Response(true, representation);
            }
        }
    }

    // Prints output corresponding to the testResponse
    static void out(Response testResponse) {
        if (testResponse.representability()) {
            System.out.println("Yes");
            printArr(testResponse.getRepresentation());
        }
        else
            System.out.println("No");
    }

    // Prints array
    static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

class Response {
    private boolean representationPossible;
    private int[] representation;
    
    // Constructors
    // If the representation is not possible, then no need to pass a representation
    public Response(boolean representationPossible) {
        this.representationPossible = representationPossible;
    }
    public Response(boolean representationPossible, int[] representation) {
        this.representationPossible = representationPossible;
        this.representation = representation;
    }

    // Getter methods
    public boolean representability() {
        return representationPossible;
    }
    public int[] getRepresentation() {
        return representation;
    }
}