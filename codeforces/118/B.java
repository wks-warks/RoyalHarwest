//Codeforces 118B 
import java.util.Scanner;

public class CF118B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        String[] handkerchief = formHandkerchief(n);
        for (String layer : handkerchief) {
            System.out.println(layer);
        }
    }

/*
    // Forms and returns the handkerchief of dimension = n
    static String[] formHandkerchief(int n) {
        String[] handkerchief = new String[2*n + 1];
        
        for (int layer = 0; layer <= n; ++layer) {
            handkerchief[layer] = "";
            for (int spaces = n - layer; spaces > 0; --spaces) {
                handkerchief[layer] += " ";
            }
            for (int num = 0; num < layer; ++num) {
                handkerchief[layer] += num + " ";
            }
            handkerchief[layer] += layer;
            for (int num = layer + 1; num <= 2 * layer; ++num) {
                handkerchief[layer] += " "  + (2*layer - num);
            }
        }
        
        for (int kerchiefLayer = n+1; kerchiefLayer <= 2*n; ++kerchiefLayer) {
            handkerchief[kerchiefLayer] = "";
            int layer = 2*n - kerchiefLayer;
            for (int spaces = n - layer; spaces > 0; --spaces) {
                handkerchief[kerchiefLayer] += " ";
            }
            for (int num = 0; num < layer; ++num) {
                handkerchief[kerchiefLayer] += num + " ";
            }
            handkerchief[kerchiefLayer] += layer;
            for (int num = layer + 1; num <= 2 * layer; ++num) {
                handkerchief[kerchiefLayer] += " "  + (2*layer - num);
            }
        }

        return handkerchief;
    }
*/

    // Forms and returns the handkerchief of dimension = n
    static String[] formHandkerchief(int n) {
        String[] handkerchief = new String[2*n + 1];
        
        for (int layer = 0; layer <= n; ++layer) {
            handkerchief[layer] = "";
            for (int spaces = n - layer; spaces > 0; --spaces) {
                handkerchief[layer] += "  ";
            }
            for (int num = 0; num < layer; ++num) {
                handkerchief[layer] += num + " ";
            }
            handkerchief[layer] += layer;
            for (int num = layer + 1; num <= 2 * layer; ++num) {
                handkerchief[layer] += " "  + (2*layer - num);
            }
        }
        
        for (int kerchiefLayer = n+1; kerchiefLayer <= 2*n; ++kerchiefLayer) {
            handkerchief[kerchiefLayer] = "";
            int layer = 2*n - kerchiefLayer;
            for (int spaces = n - layer; spaces > 0; --spaces) {
                handkerchief[kerchiefLayer] += "  ";
            }
            for (int num = 0; num < layer; ++num) {
                handkerchief[kerchiefLayer] += num + " ";
            }
            handkerchief[kerchiefLayer] += layer;
            for (int num = layer + 1; num <= 2 * layer; ++num) {
                handkerchief[kerchiefLayer] += " "  + (2*layer - num);
            }
        }

        return handkerchief;
    }
}
