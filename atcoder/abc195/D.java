import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int bagCount = Integer.parseInt(st.nextToken());
    int boxCount = Integer.parseInt(st.nextToken());
    int queryCount = Integer.parseInt(st.nextToken());

    List<Bag> bags = new ArrayList<Bag>();
    for (int b = 0; b < bagCount; b += 1) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      bags.add(new Bag(size, value));
    }

    List<Integer> boxSizes = new ArrayList<Integer>();
    st = new StringTokenizer(br.readLine());
    for (int b = 0; b < boxCount; b += 1) {
      boxSizes.add(Integer.parseInt(st.nextToken()));
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    for (int q = 0; q < queryCount; q += 1) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());

      List<Integer> allowedBoxes = new ArrayList<Integer>();
      for (int b = 0; b < boxCount; b += 1) {
        if (b + 1 < left || b >= right) {
          allowedBoxes.add(boxSizes.get(b));
        }
      }
    
      int maxValue = computeValue(bags, allowedBoxes);
      pw.println(maxValue);
    }

    pw.close();
  }

  static int computeValue(List<Bag> bags, List<Integer> allowedBoxes) {
    List<Boolean> occupied = new ArrayList<Boolean>(Collections.nCopies(allowedBoxes.size(), false));
    Collections.sort(allowedBoxes);
    Collections.sort(bags, Collections.reverseOrder(new Bag()));

    int value = 0;
    for (var bag : bags) {
      int presValue = bag.value;
      int presSize = bag.size;
      
      for (int b = 0; b < allowedBoxes.size(); b += 1) {
        if (!occupied.get(b) && allowedBoxes.get(b) >= presSize) {
          occupied.set(b, true);
          value += presValue;
          break;
        }
      }
    }

    return value;
  }
}

class Bag implements Comparator<Bag> {
  int size;
  int value;

  public Bag() {

  }

  public Bag(int size, int value) {
    this.size = size;
    this.value = value;
  }

  @Override
  public int compare(Bag first, Bag second) {
    if (first.value > second.value) {
      return 1;
    } else if (first.value == second.value) {
      return 0;
    } else {
      return -1;
    }
  }
}