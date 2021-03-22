import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    
    StringBuilder output = new StringBuilder();
    StringTokenizer st;
    for (int t = 0; t < tests; t += 1) {
      st = new StringTokenizer(br.readLine());
      int len = Integer.parseInt(st.nextToken());
      int wid = Integer.parseInt(st.nextToken());

      int wallsBroken = computeWallsBroken(len, wid);
      output.append(wallsBroken + "\n");
    }
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static int computeWallsBroken(int len, int wid) {
    return len * wid;
  }
}