// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<25).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int n = in.nextInt();
      int q = in.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }
      Sorter.sort(a);

      Set<Integer> achievableSums = new HashSet<Integer>();
      setAchievableSums(a, 0, n-1, achievableSums);

      while (q-->0) {
        int querySum = in.nextInt();
        boolean achievable = achievableSums.contains(querySum);
        out.println(achievable ? "Yes" : "No");
      }
    }
    
    in.close();
    out.close();
  }

  private static void setAchievableSums(int[] a, int start, int end, Set<Integer> achievableSums) {
    if (Math.min(start, end) < 0) {
      return;
    }
    int sum = 0;
    int limit = 1_000_000_000;
    boolean add = true;
    for (int i = start; i <= end; i++) {
      sum += a[i];
      if (sum > limit) {
        add = false;
      }
    }
    if (add) {
      achievableSums.add(sum);
    }
    if (start == end) {
      return;
    }

    int mid = getMid(a, start, end);
    // out.println(start + " " + mid + " " + end);
    if (mid == end) {
      return;
    }
    setAchievableSums(a, start, mid, achievableSums);
    setAchievableSums(a, mid+1, end, achievableSums);
  }

  private static int getMid(int[] a, int start, int end) {
    int min = a[start];
    int max = a[end];
    int pivot = (min + max) >> 1;
    int idx = Integer.MIN_VALUE;
    int left = start;
    int right = end;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (a[mid] <= pivot) {
        idx = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return idx;
  }
  
  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st != null && st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}


class Sorter {
  static void sort(int[] arr) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
 
    for (int i = 0; i < arr.length-1; i++) {
      int newPos = rand.nextInt(i, arr.length-1);
      int temp = arr[i];
      arr[i] = arr[newPos];
      arr[newPos] = temp;
    }
 
    Arrays.sort(arr);
  }

  static void reverseSort(int[] arr) {
    sort(arr);
    int mid = arr.length >> 1;

    for (int left = 0, right = arr.length-1; left < mid; left++, right--) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
    }
  }
}