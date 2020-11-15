//Codeforces 1373A 
import java.util.Scanner;

public class CF1373A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int pricePerPiece = SC.nextInt();
            int piecesPerBox = SC.nextInt();
            int pricePerBox = SC.nextInt();
            BestDeal deal = getProfitableDeal(pricePerPiece, piecesPerBox, pricePerBox);
            out(deal);
        }
    }

    // Checks and retuns deal that is profitable
    static BestDeal getProfitableDeal(int pricePerPiece, int piecesPerBox, int pricePerBox) {
        return new BestDeal(pricePerPiece < pricePerBox ? 1 : -1, pricePerBox < (long)pricePerPiece * piecesPerBox ? piecesPerBox : -1);
    }

    // Prints output corresponding to the deal
    static void out(BestDeal deal) {
        System.out.println(deal.getSinglesCheapFor() + " " + deal.getBoxesCheapFor());
    }
}

class BestDeal {
    private int singlesCheapFor, boxesCheapFor;
    public BestDeal(int singlesCheapFor, int boxesCheapFor) {
        this.singlesCheapFor = singlesCheapFor;
        this.boxesCheapFor = boxesCheapFor;
    }

    // Getter methods
    public int getSinglesCheapFor() {
        return singlesCheapFor;
    }
    public int getBoxesCheapFor() {
        return boxesCheapFor;
    }
}