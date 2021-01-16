import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int size = FR.nextInt();
      List<Integer> list1 = new ArrayList<Integer>(size);
      for (int i = 0; i < size; i += 1) {
        list1.add(FR.nextInt());
      }
      
      List<Integer> list2 = new ArrayList<Integer>(size);
      for (int i = 0; i < size; i += 1) {
        list2.add(FR.nextInt());
      }
      
      List<Long> resultList = getResultList(list1, list2, size);
      for (var element : resultList) {
        solution.append(element + "\n");
      }
    }
		PW.print(solution.toString());
    PW.close();
  }
 
  static List<Long> getResultList(List<Integer> list1, List<Integer> list2, int size) {
    List<Long> result = new ArrayList<Long>(size);
    int max1 = list1.get(0);
    result.add((long)list1.get(0)*list2.get(0));
    for (int i = 1; i < size; i += 1) {
      max1 = Math.max(max1, list1.get(i));
      result.add(Math.max(result.get(i-1), (long)max1*list2.get(i)));
    }
    return result;
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