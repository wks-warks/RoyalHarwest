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
      int sx = FR.nextInt();
      int sy = FR.nextInt();
      int tx = FR.nextInt();
      int ty = FR.nextInt();
      solution.append(horizontalFirst(sx, sy, tx, ty));
      solution.append(horizontalFirst(tx, ty, sx, sy));
      solution.append('L');
      solution.append(verticalFirst(sx-1, sy, tx, ty+1));
      solution.append('D');
      solution.append('R');
      solution.append(verticalFirst(tx+1, ty, sx, sy-1));
      solution.append('U');
    }
		PW.print(solution.toString());
    PW.close();

  }

  static String horizontalFirst(int sx, int sy, int tx, int ty) {
    StringBuilder hf = new StringBuilder();
    hf.append(horizontal(sx, tx));
    hf.append(vertical(sy, ty));
    return hf.toString();
  }

  static String verticalFirst(int sx, int sy, int tx, int ty) {
    StringBuilder vf = new StringBuilder();
    vf.append(vertical(sy, ty));
    vf.append(horizontal(sx, tx));
    return vf.toString();
  }

  static String horizontal(int sx, int tx) {
    StringBuilder h = new StringBuilder();
    if (sx < tx) {
      while (sx++ < tx) h.append('R');
    } else {
      while(tx++ < sx) h.append('L');
    }
    return h.toString();
  }
  
  static String vertical(int sy, int ty) {
    StringBuilder v = new StringBuilder();
    if (sy < ty) {
      while(sy++ < ty) v.append('U');
    } else {
      while(ty++ < sy) v.append('D');
    }
    return v.toString();
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