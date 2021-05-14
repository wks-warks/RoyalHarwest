import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int aLen = Integer.parseInt(st.nextToken());
    int bLen = Integer.parseInt(st.nextToken());

    int[] a = new int[aLen];
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
    for (var num : solutionArr) {
      pw.print(num + " ");
    }
    pw.close();
  }

  static int[] getSolutionArr(int[] a, int[] b) {
    Sorter.sort(a);
    int[] solutionArr = new int[b.length];
    
    for (int i = 0; i < solutionArr.length; i++) {
      solutionArr[i] = getValue(a, b[i]);
    }
    return solutionArr;
  }

  static int getValue(int[] a, int bi) {
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