import java.util.Scanner;

public class CF1497C1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        for (int t = 0; t < tests; t++) {
            int sum = scanner.nextInt();
            scanner.nextInt(); // 3
            int[] triplet = getTriplet(sum);
            System.out.println(triplet[0] + " " + triplet[1] + " " + triplet[2]);
        }
    }

    static int[] getTriplet(int sum) {
        if ((sum & 1) == 1) {
            return new int[] {
                1,
                sum>>1,
                sum>>1
            };
        } else {
            if (sum % 4 == 0) {
                return new int[] {
                    sum >> 1,
                    sum >> 2,
                    sum >> 2
                };
            } else {
                return new int[] {
                    2,
                    (sum>>1)-1,
                    (sum>>1)-1
                };
            }
        }
    }
}