import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int buttons = scanner.nextInt();
    List<Integer> lightingPartner = new ArrayList<Integer>(buttons);
    for (int b = 0; b < buttons; b += 1) {
      lightingPartner.add(scanner.nextInt()-1);
    }

    int pressingRequired = getMinPressingRequired(lightingPartner);
    System.out.println(pressingRequired);
  }

  // Computes and returns minimum amount of presses required to light switch 1 (2nd in 1-indexing)
  static int getMinPressingRequired(List<Integer> lightingPartner) {
    List<Boolean> alreadyPressed = new ArrayList<Boolean>(Collections.nCopies(lightingPartner.size(), false));
    int buttonPresses = 0;
    for (int b = 0; !alreadyPressed.get(b); b = lightingPartner.get(b)) {
      if (b == 1) {
        return buttonPresses;
      }
      buttonPresses += 1;
      alreadyPressed.set(b, true);
    }
    return -1;
  }
}