//Codeforces 1333A 
import java.util.Scanner;

public class CF1333A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int rows = SC.nextInt();
            int cols = SC.nextInt();
            String[] picture = getPicture(rows, cols);
            printArr(picture);
        }
    }

    //Prints picture
    static void printArr(String[] picture) {
        for (int i = 0; i < picture.length; ++i)
            System.out.println(picture[i]);
    }

    // Constructs picture according to requirement and returns the same
    static String[] getPicture(int rows, int cols) {
        String[] picture = new String[rows];
        StringBuilder sb;
        if (((rows * cols) & 1) == 1) {
            for (int r = 0; r < rows; ++r) {
                sb = new StringBuilder();
                for (int c = 0; c < cols; ++c)
                    if (((c+r)&1) == 0)
                        sb.append('B');
                    else
                        sb.append('W');
                picture[r] = sb.toString();
            }
        }
        else {
            sb = new StringBuilder("B");
            for (int c = 1; c < cols; ++c)
                if ((c&1) == 0)
                    sb.append('W');
                else
                    sb.append('B');
            picture[0] = sb.toString();

            for (int r = 1; r < rows; ++r) {
                sb = new StringBuilder();
                for (int c = 0; c < cols; ++c)
                    if (((c+r)&1) == 0)
                        sb.append('W');
                    else
                        sb.append('B');
                picture[r] = sb.toString();
            }
        }
        return picture;
    }
}