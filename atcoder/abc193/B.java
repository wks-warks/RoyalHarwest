// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int shopCount = FR.nextInt();
    List<Shop> shopList = new ArrayList<Shop>(shopCount);
    for (int s = 0; s < shopCount; s += 1) {
      int distance = FR.nextInt();
      int price = FR.nextInt();
      int stock = FR.nextInt();
      shopList.add(new Shop(distance, price, stock));
    }

    int minPrice = getMinPrice(shopList);
    solution.append(minPrice + "\n");
		PW.print(solution.toString());
    PW.close();
  }

  static int getMinPrice(List<Shop> shopList) {
    int minPrice = Integer.MAX_VALUE;
    for (var shop : shopList) {
      if (shop.canBuy()) {
        minPrice = Math.min(minPrice, shop.getPrice());
      }
    }
    return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}

class Shop {
  private int distance, price, stock;
  public Shop(int distance, int price, int stock) {
    this.distance = distance;
    this.price = price;
    this.stock = stock;
  }

  public int getPrice() {
    return price;
  }

  public boolean canBuy() {
    return stock > distance;
  }
}