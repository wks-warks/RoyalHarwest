// Codeforces 785A
import java.util.Scanner;
import java.util.stream.*;

public class CF785A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int polyhedrons = SC.nextInt();
        int[] faces = new int[polyhedrons];
        for (int i = 0; i < polyhedrons; ++i)
            faces[i] = getFaces();
        int totalFaces = IntStream.of(faces).sum();
        System.out.println(totalFaces);
    }

    // Returns number of faces of a polyhedron
    static int getFaces() {
        String polyhedron = SC.next();
        switch(polyhedron) {
            case "Tetrahedron": return 4;
            case "Cube": return 6;
            case "Octahedron": return 8;
            case "Dodecahedron": return 12;
            case "Icosahedron": return 20;
            default: return 0;
        } // Notice that we could've just compared the first character as well and that would've been sufficient
    }
}