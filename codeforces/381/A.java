// Codeforces 381A
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class CF381A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int cards = SC.nextInt(); // Number of cards
        Deque<Integer> cardValues = new LinkedList<>(); // Stores cards' values;
        for (int c = 0; c < cards; ++c)
            cardValues.addLast(SC.nextInt());
        PointsPair serejaAndDima = computePoints(cardValues);
        System.out.println(serejaAndDima.getFirst() + " " + serejaAndDima.getSecond());
    }

    // Computes and returns pair of players' points
    static PointsPair computePoints(Deque<Integer> cardValues) {
        int sereja = 0;
        int dima = 0;
        char turn = 'S'; // 'S' => Sereja picks, 'D' => Dima picks
        while (cardValues.size() > 0) {
            int leftEnd = cardValues.peekFirst();
            int rightEnd = cardValues.peekLast();
            int pick = (leftEnd > rightEnd) ? cardValues.pollFirst() : cardValues.pollLast(); // Picking the one with greater value (greedy approach)
            if (turn == 'S') {
                sereja += pick;
                turn = 'D';
            }
            else {
                dima += pick;
                turn = 'S';
            }
        }
        return (new PointsPair(sereja, dima));
    }
}

class PointsPair {
    private int player1Points;
    private int player2Points;
    public PointsPair(int player1Points, int player2Points) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
    }
    public int getFirst() {
        return player1Points;
    }
    public int getSecond() {
        return player2Points;
    }
}