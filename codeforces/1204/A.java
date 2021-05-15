import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String arrivalTime = br.readLine();
    int trainsMissed = countTrainsMissed(arrivalTime);

    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    pw.println(trainsMissed);
    pw.close();
  }

  static int countTrainsMissed(String arrivalTime) {
    int trainsMissed = (arrivalTime.length()+1)>>1;
    if ((arrivalTime.length()&1) == 1) {
      boolean toReduce = true;

      for (int i = 1; i < arrivalTime.length(); i++) {
        if (arrivalTime.charAt(i) == '1') {
          toReduce = false;
          break;
        }
      }

      if (toReduce) {
        trainsMissed -= 1;
      }
    }
    return trainsMissed;
  }
}