import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(br.readLine());

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.println(countCommas(n));    
    pw.close();
  }

  static long countCommas(long n) {
    List<Long> limits = new ArrayList<Long>();
    List<Integer> commasPerNum = new ArrayList<Integer>();
    
    long num = 0;
    int commas = 0;
    while (num <= 1_000_000_000_000_000L) {
      limits.add(num);
      commasPerNum.add(commas);

      num = num * 1000 + 999;
      commas += 1;
    }

    long commaCount = 0;
    for (int cpn = 0; cpn < commasPerNum.size(); cpn += 1) {
      long limit = limits.get(cpn);

      if (limit >= n) {
        break;
      } else {
        long minCandidate = limit * 1000 + 999;
        commaCount += (Math.min(n, minCandidate) - limit) * cpn;
      }
    }

    return commaCount;
  }
}