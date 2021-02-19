import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int itemCount = scanner.nextInt();
    int bagStrength = scanner.nextInt();
    List<Item> itemList = new ArrayList<Item>(itemCount);
    for (int i = 0; i < itemCount; i += 1) {
      itemList.add(Item.read(scanner));
    }
    int maxValue = getMaxValue(itemList, bagStrength);
    System.out.println(maxValue);
  }

  static int getMaxValue(List<Item> itemList, int bagStrength) {
    Map<Integer, Integer> weightAndValue = new TreeMap<Integer, Integer>();
    weightAndValue.put(0, 0);
    for (var item : itemList) {
      int weight = item.weight;
      int value = item.value;
      Map<Integer, Integer> newWeightAndValue = new TreeMap<Integer, Integer>();
      for (var mapEntry : weightAndValue.entrySet()) {
        int newWeight = mapEntry.getKey() + weight;
        if (newWeight > bagStrength) {
          break;
        }
        int newValue = mapEntry.getValue() + value;
        if (weightAndValue.containsKey(newWeight)) {
          newValue = Math.max(weightAndValue.get(newWeight), newValue);
        }
        newWeightAndValue.put(newWeight, newValue);
      }
      for (var mapEntry : newWeightAndValue.entrySet()) {
        weightAndValue.put(mapEntry.getKey(), mapEntry.getValue());
      }
    }
    int maxValue = 0;
    for (var mapEntry : weightAndValue.entrySet()) {
      maxValue = Math.max(maxValue, mapEntry.getValue());
    }
    return maxValue;
  }
}

class Item {
  int weight;
  int value;
  public Item(int weight, int value) {
    this.weight = weight;
    this.value = value;
  }
  static Item read(Scanner scanner) {
    int weight = scanner.nextInt();
    int value = scanner.nextInt();
    return new Item(weight, value);
  }
}