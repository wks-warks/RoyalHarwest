//Codeforces 1207A 
import java.util.Scanner;

public class CF1207A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            Ingredients ingredients = new Ingredients();
            ingredients.setBuns(SC.nextInt());
            ingredients.setPatties(SC.nextInt());
            ingredients.setCutlets(SC.nextInt());

            Prices prices = new Prices();
            prices.setHamburgerPrice(SC.nextInt());
            prices.setChickenBurgerPrice(SC.nextInt());
            
            int maxProfit = computeMaxProfit(ingredients, prices);
            System.out.println(maxProfit);
        }
    }

    // Computes and returns max. profit on the basis of ingredients and burger prices
    static int computeMaxProfit(Ingredients ingredients, Prices prices) {
        int hamburgerPrice = prices.getHamburgerPrice();
        int chickenBurgerPrice = prices.getChickenBurgerPrice();
        int buns = ingredients.getBuns();
        int patties = ingredients.getPatties();
        int cutlets = ingredients.getCutlets();
        // We try to make the maximum possible amount of the burger that sells for a higher price
        int hamburgers, chickenBurgers;
        if (hamburgerPrice > chickenBurgerPrice) {
            hamburgers = Math.min(buns/2, patties);
            buns -= hamburgers * 2;
            chickenBurgers = Math.min(buns/2, cutlets);
        }
        else {
            chickenBurgers = Math.min(buns/2, cutlets);
            buns -= chickenBurgers * 2;
            hamburgers = Math.min(buns/2, patties);
        }
        return chickenBurgers*prices.getChickenBurgerPrice() + hamburgers*prices.getHamburgerPrice();
    }
}

class Ingredients {
    private int buns, patties, cutlets;

    // Setter methods
    public void setBuns(int buns) {
        this.buns = buns;
    }
    public void setPatties(int patties) {
        this.patties = patties;
    }
    public void setCutlets(int cutlets) {
        this.cutlets = cutlets;
    }

    // Getter methods
    public int getBuns() {
        return buns;
    }
    public int getPatties() {
        return patties;
    }
    public int getCutlets() {
        return cutlets;
    }
}

class Prices {
    private int hamburgerPrice, chickenBurgerPrice;

    // Setter methods
    public void setHamburgerPrice(int hamburgerPrice) {
        this.hamburgerPrice = hamburgerPrice;
    }
    public void setChickenBurgerPrice(int chickenBurgerPrice) {
        this.chickenBurgerPrice = chickenBurgerPrice;
    }

    // Getter methods
    public int getHamburgerPrice() {
        return hamburgerPrice;
    }
    public int getChickenBurgerPrice() {
        return chickenBurgerPrice;
    }
}