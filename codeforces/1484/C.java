import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    StringTokenizer st;
    for (int t = 0; t < tests; t += 1) {
      st = new StringTokenizer(br.readLine());
      int friends = Integer.parseInt(st.nextToken());
      int days = Integer.parseInt(st.nextToken());

      List<List<Integer>> availableFriends = new ArrayList<List<Integer>>(days);
      for (int d = 0; d < days; d += 1) {
        st = new StringTokenizer(br.readLine());
        int availableCount = Integer.parseInt(st.nextToken());
        availableFriends.add(new ArrayList<Integer>(availableCount));
        
        for (int f = 0; f < availableCount; f += 1) {
          availableFriends.get(d).add(Integer.parseInt(st.nextToken()));
        }
      }

      Response response = getResponse(days, friends, availableFriends);
      output.append(response);
    }
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static Response getResponse(int days, int friends, List<List<Integer>> availableFriends) {
    List<Integer> friendsIdx = new ArrayList<Integer>(Collections.nCopies(days, -1));
    List<Integer> frequency = new ArrayList<Integer>(Collections.nCopies(friends + 1, 0));
    int limit = (days + 1) >> 1;

    for (int d = 0; d < days; d += 1) {
      if (availableFriends.get(d).size() == 1) {
        int friendIdx = availableFriends.get(d).get(0);
        int presCount = frequency.get(friendIdx);
        if (presCount == limit) {
          return new Response(false, null);
        }
        frequency.set(friendIdx, presCount + 1);
        friendsIdx.set(d, friendIdx);
      }
    }

    for (int d = 0; d < days; d += 1) {
      if (availableFriends.get(d).size() > 1) {
        Friend partner = new Friend();
        partner.freq = Integer.MAX_VALUE;
        partner.idx = -1;
        
        for (var friendIdx : availableFriends.get(d)) {
          int freq = frequency.get(friendIdx);
          if (freq < limit && freq < partner.freq) {
            partner.idx = friendIdx;
            partner.freq = freq;
          }
        }

        if (partner.idx == -1) {
          return new Response(false, null);
        }
        frequency.set(partner.idx, partner.freq + 1);
        friendsIdx.set(d, partner.idx);
      }
    }

    return new Response(true, friendsIdx);
  }
}

class Response {
  boolean possible;
  List<Integer> friendsIdx;
  
  public Response() {

  }

  public Response(boolean possible, List<Integer> friendsIdx) {
    this.possible = possible;
    this.friendsIdx = friendsIdx;
  }

  @Override
  public String toString() {
    if (possible) {

      StringBuilder sb = new StringBuilder();
      sb.append("YES\n");
      
      for (var friendIdx : friendsIdx) {
        sb.append(friendIdx + " ");
      }

      sb.append("\n");
      return sb.toString();
    } else {

      return "NO\n";
    }
  }
}

class Friend {
  int freq;
  int idx;
}