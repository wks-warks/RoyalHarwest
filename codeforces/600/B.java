import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int aLen = Integer.parseInt(st.nextToken());
    int bLen = Integer.parseInt(st.nextToken());

    Integer[] a = new Integer[aLen];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < aLen; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    int[] b = new int[bLen];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < bLen; i++) {
      b[i] = Integer.parseInt(st.nextToken());
    }

    int[] solutionArr = getSolutionArr(a, b);
    StringBuilder sb = new StringBuilder();
    for (var num : solutionArr) {
      sb.append(num + " ");
    }
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(sb.toString());
    pw.close();
  }

  static int[] getSolutionArr(Integer[] a, int[] b) {
    Arrays.sort(a);
    int[] solutionArr = new int[b.length];
    
    for (int i = 0; i < solutionArr.length; i++) {
      solutionArr[i] = getValue(a, b[i]);
    }
    return solutionArr;
  }

  static int getValue(Integer[] a, int bi) {
    int left = 0;
    int right = a.length-1;
    int ans = 0;

    while (left <= right) {
      int mid = (left + right) >> 1;
      if (a[mid] <= bi) {
        left = mid + 1;
        ans = left;
      } else {
        right = mid - 1;
      }
    }
    return ans;
  }
}