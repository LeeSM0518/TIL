package midterm_exam;

import java.util.Arrays;
import java.util.Vector;

public class raw2ppm {
    public static void main(String[] args) {
        int [] r = {255,0,0, 0,255,0, 0,0,255};
        int [] g = {0,0,255, 0,255,0, 255,0,0};
        int [] b = {0,255,0, 255,255,255, 0,255,0};

        Vector v = new Vector<>();

        int[] ar = new int[r.length * 3];

        for(int i=0; i < r.length; i++) {
            v.add(r[i]);
            v.add(g[i]);
            v.add(b[i]);
        }

        for(int i=0; i < v.size(); i++) {
            ar[i] = (int) v.get(i);
        }

        System.out.print("P3 3 3 255\n");
        Arrays.stream(ar).forEach(s -> {
            System.out.print(s + " ");
        });
    }
}
