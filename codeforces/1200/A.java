import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    scanner.nextInt();

    List<Boolean> roomsOccupied = new ArrayList<Boolean>(Collections.nCopies(10, false));
    String events = scanner.next();

    for (var event : events.toCharArray()) {
      updateRoom(roomsOccupied, event);
    }

    for (var status : roomsOccupied) {
      System.out.print(status ? 1 : 0);
    }
    System.out.println();
  }

  static void updateRoom(List<Boolean> roomsOccupied, char event) {
    if (event == 'L') {
      for (int i = 0; i < roomsOccupied.size(); i += 1) {
        if (!roomsOccupied.get(i)) {
          roomsOccupied.set(i, true);
          break;
        }
      }
    } else if (event == 'R') {
      for (int i = roomsOccupied.size() - 1; i >= 0; i -= 1) {
        if (!roomsOccupied.get(i)) {
          roomsOccupied.set(i, true);
          break;
        }
      }
    } else {
      roomsOccupied.set(event - '0', false);
    }
  }
}