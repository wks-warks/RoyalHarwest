// Codeforces 510A
import java.util.Scanner;

public class CF510A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int rows = SC.nextInt();
        int cols = SC.nextInt();
        String[] snake = getSnake(rows, cols);
        out(snake);
    }

    // Builds and returns snake string-array
    static String[] getSnake(int rows, int cols) {
        String[] snake = new String[rows];
        String fullRow = getFullRow(cols);
        String leftEnd = getLeftEnd(cols);
        String rightEnd = getRightEnd(cols);
        for (int i = 0; i < rows; ++i) {
            if (i % 2 == 0) // Keeping the most probable check first in line to minimize checks
                snake[i] = fullRow;
            else if (i % 4 == 1)
                snake[i] = rightEnd;
            else
                snake[i] = leftEnd;
        }
        return snake;
    }

    // Returns string with all elements occupied by the snake's body
    static String getFullRow(int cols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols; ++i)
            sb.append('#');
        return sb.toString();
    }

    // Returns string with leftMost element occupied by the snake's body
    static String getLeftEnd(int cols) {
        StringBuilder sb = new StringBuilder("#");
        for (int i = 1; i < cols; ++i)
            sb.append('.');
        return sb.toString();
    }

    // Returns string with rightMost element occupied by the snake's body
    static String getRightEnd(int cols) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < cols; ++i)
            sb.append('.');
        sb.append('#');
        return sb.toString();
    }

    // Prints snake in the required format
    static void out(String[] snake) {
        for (int i = 0; i < snake.length; ++i)
            System.out.println(snake[i]);
    }
}