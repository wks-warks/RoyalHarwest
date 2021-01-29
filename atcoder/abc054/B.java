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
      int imageDim = FR.nextInt();
      int templateDim = FR.nextInt();
      char[][] image = new char[imageDim][];
      for (int i = 0; i < imageDim; i += 1) {
        image[i] = FR.next().toCharArray();
      }
      char[][] template = new char[templateDim][];
      for (int i = 0; i < templateDim; i += 1) {
        template[i] = FR.next().toCharArray();
      }
      
      boolean hasTemplate = checkForTemplate(image, template);
      solution.append(hasTemplate ? "Yes" : "No");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static boolean checkForTemplate(char[][] image, char[][] template) {
    for (int i = 0; i <= image.length-template.length; ++i) {
      for (int j = 0; j <= image.length-template.length; ++j) {
        boolean contained = true;
        for (int i2 = 0; i2 < template.length; ++i2) {
          for (int j2 = 0; j2 < template.length; ++ j2) {
            if (image[i+i2][j+j2] != template[i2][j2]) contained = false;
          }
        }
        if (contained) return true;
      }
    }
    return false;
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