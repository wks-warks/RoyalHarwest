import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
// import java.util.TreeMap;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int spheres = Integer.parseInt(br.readLine());
    int[] spherePrices = new int[spheres];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int s = 0; s < spheres; s++) {
      spherePrices[s] = Integer.parseInt(st.nextToken());
    }

    Solution solution = Solution.getSolution(spherePrices);
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(solution.toString());
    pw.close();
  }
}

class Solution {
  int spheresBought;
  int[] pricingOrder;

  public Solution() {
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(spheresBought + "\n");
    for (var price : pricingOrder) {
      sb.append(price + " ");
    }
    return sb.toString();
  }

  static Solution getSolution(int[] spherePrices) {
    Sorter.sort(spherePrices);
    Solution solution = new Solution();
    solution.pricingOrder = new int[spherePrices.length];

    int spIdx = spherePrices.length-1;
    for (int i = 0; i < solution.pricingOrder.length; i += 2) {
      solution.pricingOrder[i] = spherePrices[spIdx--];
    }
    for (int i = 1; i < solution.pricingOrder.length; i += 2) {
      solution.pricingOrder[i] = spherePrices[spIdx--];
    }

    for (int i = 1; i < solution.pricingOrder.length-1; i++) {
      if (solution.pricingOrder[i] < solution.pricingOrder[i-1] && solution.pricingOrder[i] < solution.pricingOrder[i+1]) {
        solution.spheresBought++;
      }
    }

    return solution;
  }
}

class Sorter {
  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int r = i + (int) (Math.random() * (arr.length - i));
      int temp = arr[i];
      arr[i] = arr[r];
      arr[r] = temp;
    }
    Arrays.sort(arr);
  }
}