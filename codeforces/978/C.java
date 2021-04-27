import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int dormitories = scanner.nextInt();
    int letters = scanner.nextInt();

    long[] dormitorySizes = new long[dormitories];
    for (int d = 0; d < dormitories; d++) {
      dormitorySizes[d] = scanner.nextLong();
    }

    long[] letterAddresses = new long[letters];
    for (int lr = 0; lr < letters; lr++) {
      letterAddresses[lr] = scanner.nextLong();
    }

    long[][] deliveryAddresses = getDeliveryAddresses(dormitorySizes, letterAddresses);
    StringBuilder solution = new StringBuilder();
    for (var address : deliveryAddresses) {
      solution.append(address[0] + " " + address[1] + "\n");
    }
    System.out.print(solution.toString());
  }

  static long[][] getDeliveryAddresses(long[] dormitorySizes, long[] letterAddresses) {
    long[][] deliveryAddresses = new long[letterAddresses.length][2];
    long[] dormSizePrefix = getPrefixSum(dormitorySizes);

    for (int lr = 0; lr < letterAddresses.length; lr++) {
      int dormitory = findDormitory(dormSizePrefix, letterAddresses[lr]);
      deliveryAddresses[lr][0] = dormitory;
      deliveryAddresses[lr][1] = letterAddresses[lr] - dormSizePrefix[dormitory-1];
    }

    return deliveryAddresses;
  }

  static int findDormitory(long[] dormSizePrefix, long letterAddress) {
    int left = 0;
    int right = dormSizePrefix.length - 1;
    int dormitory = -1;

    while (left <= right) {
      int mid = (left + right) >> 1;
      
      if (dormSizePrefix[mid] >= letterAddress) {
        dormitory = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return dormitory;
  }

  static long[] getPrefixSum(long[] arr) {
    long[] prefixSum = new long[arr.length+1];
    for (int i = 1; i <= arr.length; i++) {
      prefixSum[i] = prefixSum[i-1] + arr[i-1];
    }
    return prefixSum;
  }
}