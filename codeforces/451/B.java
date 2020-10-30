//Codeforces 451B 
import java.util.Scanner;

public class CF451B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int arrLen = SC.nextInt();
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; ++i)
            arr[i] = SC.nextInt();
        Response testResponse = getResponse(arr);
        out(testResponse);
    }

    // Finds and returns test-case-response
    static Response getResponse(int[] arr) {        
        // Defaults
        boolean isPossible = true;
        int segStart = 0;
        int segEnd = 0;

        // Finding start of segment to be reversed and maximum of elements before it
        int maxBefore = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length-1; ++i) {
            if (arr[i] > arr[i+1]) {
                break;
            }
            maxBefore = Math.max(maxBefore, arr[i]);
            segStart += 1;
        }

        // Finding end of segment and verifying that all numbers of segment are larger than any before it
        int maxOfSeg = arr[segStart];
        segEnd = segStart;
        for (int i = segStart+1; i < arr.length; ++i) {
            if (arr[i] < maxBefore){
                isPossible = false;
                break;
            }
            maxOfSeg = Math.max(maxOfSeg, arr[i]);
            if (arr[i] > arr[i-1])
                break;
            segEnd += 1;
        }
        if (isPossible) {
            // Verifying that all elements after end of reversed segmenr are in order and each of them is larger than the maximum element of reversed segment
            for (int i = segEnd+1; i < arr.length; ++i)
                if (arr[i] < arr[i-1] || arr[i] < maxOfSeg) {
                    isPossible = false;
                    break;
                }
        }
        if (isPossible)
            return new Response(isPossible, segStart+1, segEnd+1); // Converting to 1-idx
        else
            return new Response(isPossible);
    }



    // Prints output corresponding to test-response
    static void out(Response testResponse) {
        if (testResponse.isPossible()) {
            System.out.println("yes");
            System.out.println(testResponse.startIdx() + " " + testResponse.endIdx());
        }
        else
            System.out.println("no");
    }
}

// Response of test cases
class Response {
    private boolean possibility;
    private int segStart;
    private int segEnd;
    public Response(boolean possibility) {
        this.possibility = possibility;
    }
    public Response(boolean possibility, int segStart, int segEnd) {
        this.possibility = possibility;
        this.segStart = segStart;
        this.segEnd = segEnd;
    }
    // Returns possibility
    public boolean isPossible() {
        return possibility;
    }
    // Returns start index of reversed portion
    public int startIdx() {
        return segStart;
    }
    // Returns end index of reversed portion
    public int endIdx() {
        return segEnd;
    }
}