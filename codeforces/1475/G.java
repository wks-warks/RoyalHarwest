import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int arrLen = FR.nextInt();
      Integer[] arr = new Integer[arrLen];
      for (int i = 0; i < arrLen; ++i) {
        arr[i] = FR.nextInt();
      }
      int toRemove = getRemoveCount(arr);
      solution.append(toRemove + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getRemoveCount(Integer[] arr) {
    int[] freq = new int[200_001];
    int[] keep = new int[200_001];
    Set<Integer> arrNums = new TreeSet<Integer>();
    for (var element : arr) {
      freq[element] += 1;
      keep[element] += 1;
      arrNums.add(element);

    }
    for (var element : arrNums) {
      for (int i = element<<1; i < 200_001; i += element) {
        keep[i] = Math.max(keep[i], keep[element] + freq[i]);
      }
    }

    int maxKept = 0;
    int idx = 0;
    for (var element : keep) {
      maxKept = Math.max(maxKept, element);
    }
    return arr.length - maxKept;
  }

  static int sqrt(int num) {
    int left = 1;
    int right = num;
    int root = 1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if ((long)mid * mid <= num) {
        root = mid;
        left = mid+1;
      } else {
        right = mid-1;
      }
    }
    return root;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;
  
    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }
  
    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }
  
    int nextInt() {
      return Integer.parseInt(next());
    }
  
    long nextLong() {
      return Long.parseLong(next());
    }
  
    double nextDouble() {
      return Double.parseDouble(next());
    }
 
    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}

/*
k1          v89023
k2          v50208
k6          v20
k86         v1592
k3389       v18
k25189      v5849
k75567      v1320
k89087      v1
k102389     v1
k103483     v8
k125393     v63
k131659     v103
k138238     v2
k151134     v4041
k157362     v1
k159603     v43292
k178411     v26
k193009     v3747
k196613     v685

Expected answer : 56708
*/