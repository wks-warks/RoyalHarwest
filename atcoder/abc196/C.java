import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(br.readLine());

    int answer = getAnswer(n);

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(answer);
    pw.close();
  }

  static int getAnswer(long n) {
    int answer = 0;
    int i = 1;
    
    while(true) {
      long mirrored = Long.parseLong(i + "" + i);
      if (mirrored > n) {
        break;
      } else {
        answer += 1;
      }
      i += 1;
    }

    return answer;
  }
}