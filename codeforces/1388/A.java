//Codeforces 1388A 
import java.util.Scanner;

public class CF1388A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int number = SC.nextInt();
            Response testResponse = checkNearPrimality(number);
            out(testResponse);
        }
    }

    // Computes and returns response for test case
    static Response checkNearPrimality(int num) {
        if (num <= 30)
            return new Response(false);
        else {
            int[] numbers = {num-30, 6, 10, 14};
            boolean satisfactoryDistribution = true; 
            for (int i = 1; i <= 3; ++i) // Checking to see if all numbers are distinct
                if (numbers[0] == numbers[i])
                satisfactoryDistribution = false;
            if (!satisfactoryDistribution) {
                numbers[3] += 1;
                numbers[0] -= 1;
            }
            return new Response(true, numbers);
        }
    }

    // Prints response to test case
    static void out(Response testResponse) {
        if (testResponse.nearPrimality()) {
            System.out.println("YES");
            System.out.println(testResponse.numbersString());
        }
        else
            System.out.println("NO");
    }
}

// Class to store test case response
class Response {
    private boolean isNearlyPrime = true;
    private int[] nums;
    public Response(boolean isNearlyPrime) {
        this.isNearlyPrime = isNearlyPrime;
    }
    public Response(boolean isNearlyPrime, int[] nums) {
        this.isNearlyPrime = isNearlyPrime;
        this.nums = nums;
    }

    // Returns near primality
    public boolean nearPrimality() {
        return isNearlyPrime;
    }
    // Returns numbers composing the near-primal
    public String numbersString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; ++i)
            sb.append(nums[i] + " ");
        return sb.toString();
    }
}