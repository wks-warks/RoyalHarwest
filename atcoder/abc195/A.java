import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int m = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(canDefeat(m, h) ? "Yes" : "No");    
    pw.close();
  }

  static boolean canDefeat(int m, int h) {
    return h % m == 0;
  }
}