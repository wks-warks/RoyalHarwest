// Codeforces 456A
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Set;

public class CF456A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int laptops = SC.nextInt();
        int[][] priceXquality = new int[laptops][2];
        for (int i = 0; i < laptops; ++i) {
            priceXquality[i][0] = SC.nextInt(); // Price
            priceXquality[i][1] = SC.nextInt(); // Quality
        }
        boolean alexIsRight = checkAlexCase(priceXquality);
        out(alexIsRight);
    }

    // Checks and returns whether or not alex is right
    static boolean checkAlexCase(int[][] priceXquality) {
        TreeMap<Integer, Integer> priceXLeastQuality = new TreeMap<>();
        TreeMap<Integer, Integer> priceXBestQuality = new TreeMap<>();
        for (int i = 0; i < priceXquality.length; ++i) {
            int price = priceXquality[i][0];
            int skill = priceXquality[i][1];
            if (priceXLeastQuality.containsKey(price)) {
                priceXLeastQuality.put(price, Math.min(price, priceXLeastQuality.get(price)));
                priceXBestQuality.put(price, Math.max(price, priceXBestQuality.get(price)));
            }
            else {
                priceXLeastQuality.put(price, skill);
                priceXBestQuality.put(price, skill);
            }
        }
        // Finding satisfactory pair of laptops under Alex's conditions
        int maxSkillThusFar = 0;
        Set<Integer> prices = priceXLeastQuality.keySet();
        for (int presentPrice : prices) {
            int presentWorst = priceXLeastQuality.get(presentPrice);
            if (maxSkillThusFar > presentWorst)
                return true;
            int presentBest = priceXBestQuality.get(presentPrice);
            maxSkillThusFar = Math.max(maxSkillThusFar, presentBest);
        }
        return false; // Couldn't find satisfactory pair of laptops under Alex's conditions
    }

    // Prints output corresponding to condition
    static void out(boolean alexIsRight) {
        if (alexIsRight)
            System.out.println("Happy Alex");
        else
            System.out.println("Poor Alex");
    }
}