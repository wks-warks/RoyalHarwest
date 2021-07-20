// Author : RegalBeast

import java.io.*;
import java.awt.Point;
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
    int n = in.nextInt();
    int k = in.nextInt();

    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    int unnecessary = correctCountUnnecessary(n, k, a);
    out.println(unnecessary);
    in.close();
    out.close();
  }

  static int countUnnecessary(int n, int k, int[] a) {
    Sorter.sort(a);
    List<TreeSet<Integer>> sumPrefix = new ArrayList<TreeSet<Integer>>(n);
    List<TreeSet<Integer>> sumSuffix = new ArrayList<TreeSet<Integer>>(n);
    for (int i = 0; i < n; i++) {
      sumPrefix.add(new TreeSet<Integer>());
      sumSuffix.add(new TreeSet<Integer>());
    }

    TreeSet<Integer> prev = new TreeSet<Integer>();
    prev.add(0);
    for (int i = 0; i < n; i++) {
      for (var num : prev) {
        if (num + a[i] < k) {
          sumPrefix.get(i).add(num + a[i]);
        }
        sumPrefix.get(i).add(num);
      }
      prev = sumPrefix.get(i);
    }

    prev = new TreeSet<Integer>();
    prev.add(0);
    for (int i = n-1; i >= 0; i--) {
      for (var num : prev) {
        if (num + a[i] < k) {
          sumSuffix.get(i).add(num + a[i]);
        }
        sumSuffix.get(i).add(num);
      }
      prev = sumSuffix.get(i);
    }

    int left = 0;
    int right = n-1;
    int unnecessary = 0;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (isUnnecessary(mid, sumPrefix, sumSuffix, a, k)) {
        unnecessary = mid + 1;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return unnecessary;
  }

  static boolean isUnnecessary(int idx, List<TreeSet<Integer>> sumPrefix, List<TreeSet<Integer>> sumSuffix, int[] a, int threshold) {
    TreeSet<Integer> zero = new TreeSet<Integer>();
    zero.add(0);
    Set<Integer> prevSet = (idx == 0 ? zero : sumPrefix.get(idx-1));
    TreeSet<Integer> nextSet = (idx == a.length-1 ? zero : sumSuffix.get(idx+1));

    boolean necessary = false;
    for (var prev : prevSet) {
      Integer ceil = nextSet.ceiling(threshold - a[idx] - prev);
      necessary |= ceil != null && prev + ceil < threshold;
    }

    return !necessary;
  }

  static int correctCountUnnecessary(int n, int k, int[] a) {
    Sorter.sort(a);
 
    int unnecessary = 0;
    int left = 0;
    int right = n-1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (correctIsUnnecessary(mid, n, k, a)) {
        unnecessary = mid + 1;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return unnecessary;
  }
 
  static boolean correctIsUnnecessary(int pos, int n, int k, int[] a) {
    BitSet bs = new BitSet(k);
    bs.set(0);
 
    for (int i = 0; i < n; i++) {
      if (i != pos) {
        for (int j = k-a[i]-1; j >= 0; j--) {
          if (bs.get(j)) {
            bs.set(j+a[i]);
            if (k - a[pos] <= j+a[i]) {
              return false;
            }
          }
        }
      }
    }
    return a[pos] <= k-1;
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