//Codeforces 686A 
import java.util.Scanner;

public class CF686A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int queueLen = SC.nextInt();
        int stock = SC.nextInt();
        int[] exchange = new int[queueLen]; // Ice cream exchanged with Kay and Gerda (give/take)
        for (int p = 0; p < queueLen; ++p) {
            char type = SC.next().charAt(0);
            int value = SC.nextInt();
            exchange[p] = type == '+' ? value: -value;
        }
        Response endResponse = getEndResponse((long)stock, exchange);
        out(endResponse);    
    }

    // Prints output corresponding to response
    static void out(Response endResponse) {
        System.out.print(endResponse.getStockLeft() + " ");
        System.out.println(endResponse.getUnhappyCount());
    }

    // Computes and returns values concerning Kay and Gerda
    static Response getEndResponse(long stock, int[] exchange) {
        int unhappyKids = 0;
        for (int k = 0; k < exchange.length; ++k)
            if (exchange[k] > 0)
                stock += exchange[k];
            else if (stock + exchange[k] < 0)
                unhappyKids += 1;
            else
                stock += exchange[k];
        return new Response(stock, unhappyKids);
    }
    
}

// Response concerning Kay and Gerda respectively
class Response {
    private long stockLeft;
    private int unhappyKids;
    
    public Response(long stockLeft, int unhappyKids) {
        this.stockLeft = stockLeft;
        this.unhappyKids = unhappyKids;
    }

    // Getter methods
    public long getStockLeft() {
        return stockLeft;
    }
    public int getUnhappyCount() {
        return unhappyKids;
    }
}