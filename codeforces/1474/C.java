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
      int n = FR.nextInt();
      int elements = 2*n;
      TreeMap<Integer, Integer> frequency = new TreeMap<Integer, Integer>(Collections.reverseOrder());
      for (int i = 0; i < elements; i += 1) {
        int num = FR.nextInt();
        frequency.put(num, frequency.containsKey(num) ? frequency.get(num)+1 : 1);
      }
      Deque<Pair> removals = null;
      TreeMap<Integer, Integer> frequencyClone = new TreeMap<Integer, Integer>(frequency);
      for (var element : frequencyClone.entrySet()) {
        int key = element.getKey();
        int value = element.getValue();
        if (key == frequency.firstKey()) {
          if (value == 1)
            continue;
        }
        if (value == 1) {
          frequency.remove(key);
          removals = getRemovals(new TreeMap<Integer, Integer>(frequency), elements);
          frequency.put(key, 1);
        } else {
          frequency.put(key, value-1);
          removals = getRemovals(new TreeMap<Integer, Integer>(frequency), elements);
          frequency.put(key, value);
        }
        if (removals != null) {
          Pair pair = removals.poll();
          pair.first = key;
          removals.addFirst(pair);
          break;
        }
      }
      
      if (removals != null) {
        solution.append("YES\n");
        Pair pair = removals.peek();
        int sum = pair.first + pair.second;
        solution.append(sum + "\n");
        while (removals.size() > 0) {
          pair = removals.poll();
          solution.append(pair.first + " ");
          solution.append(pair.second + "\n");
        }
      } else {
        solution.append("NO\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static Deque<Pair> getRemovals(TreeMap<Integer, Integer> frequency, int elements) {
    Deque<Pair> removed = new ArrayDeque<Pair>(elements/2);
    int sum = frequency.firstKey();
    int count = frequency.get(sum);
    if (count > 1) return null;
    frequency.remove(sum);
    removed.add(new Pair(-1, sum));
    while (frequency.size() > 0) {
      int larger = frequency.firstKey();
      count = frequency.get(larger);
      if (count == 1) {
        frequency.remove(larger);
      } else {
        frequency.put(larger, count-1);
      }
      int smaller = sum - larger;
      if (frequency.containsKey(smaller)) {
        count = frequency.get(smaller);
        if (count == 1) {
          frequency.remove(smaller);
        } else {
          frequency.put(smaller, count-1);
        }
      } else {
        return null;
      }
      removed.add(new Pair(smaller, larger));
      sum = larger;
    }
    return removed;
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

class Pair {
  int first;
  int second;
  public Pair() {

  }
  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}