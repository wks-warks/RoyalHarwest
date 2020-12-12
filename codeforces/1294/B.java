//Codeforces 1294B 
import java.util.Scanner;
import java.util.Arrays;

public class CF1294B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int packages = SC.nextInt();
            int[][] packageCoordinates = new int[packages][2];
            
            for (int p = 0; p < packages; ++p) {
                packageCoordinates[p][0] = SC.nextInt();
                packageCoordinates[p][1] = SC.nextInt();
            }
            String path = getPath(packages, packageCoordinates);
            if (path != null) {
                System.out.println("YES\n" + path);
            }
            else {
                System.out.println("NO");
            }
        }
    }

    // Returns lexicographically shortest path if one exists, null otherwise
    static String getPath(int packages, int[][] packageCoordinates) {
        Arrays.sort(packageCoordinates, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        int x = 0;
        int y = 0;
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < packages; ++i) {
            if (packageCoordinates[i][1] < y)
                return null;
            for (int r = 0; r < packageCoordinates[i][0] - x; ++r)
                path.append('R');
            for (int u = 0; u < packageCoordinates[i][1] - y; ++u)
                path.append('U');
            x = packageCoordinates[i][0];
            y = packageCoordinates[i][1];
        }
        return path.toString();
    }
}
