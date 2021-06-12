import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Font;

public class TraceQuick_ {

    private static int line = 0;


    public static void sort(String[] a) {
        sort3way(a, 0, a.length - 1);
    }

    private static void sort3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        Comparable v = a[lo];
        int lt = lo, i = lo + 1, gt = hi;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i);
            else i++;
        }
        sort3way(a, lo, lt - 1);
        sort3way(a, gt + 1, hi);
    }

    private static void sort(String[] a, int lo, int hi) {
        if (hi < lo) return;
        if (hi == lo) {
            draw(a, lo, lo, hi);
            line++;
            return;
        }
        int j = partition(a, lo, hi);
        draw(a, lo, j, hi);
        line++;
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static void draw(String[] a, int lo, int j, int hi) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-3.75, line, lo + "");
        StdDraw.text(-2.50, line, j + "");
        StdDraw.text(-1.25, line, hi + "");
        for (int i = 0; i < a.length; i++) {
            if (i == j) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else if (i >= lo && i <= hi) StdDraw.setPenColor(StdDraw.BLACK);
            else StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.text(i, line, a[i]);
        }
    }

    private static int partition(String[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        String v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }


    // exchange a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    private static void header(String[] a) {
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(n / 2.0, -3, "a[ ]");
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -2, i + "");
        StdDraw.text(-2.50, -2, "j");
        StdDraw.text(-1.25, -2, "hi");
        StdDraw.text(-3.75, -2, "lo");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-3, -1.65, n - 0.5, -1.65);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -1, a[i]);
    }

    // display footer
    private static void footer(String[] a, int j) {
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(-1.25, line, j + "");
        for (int i = 0; i < a.length; i++) {
            if (i == j) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(i, line, a[i]);
        }
    }


    // test client
    public static void main(String[] args) {

        // parse command-line argument as an array of 1-character strings
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i + 1);

        // set canvas size
        StdDraw.setCanvasSize(30 * (n + 3), 30 * (n + 3));
        StdDraw.setXscale(-4, n + 1);
        StdDraw.setYscale(n + 1, -4);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));

        // display the header
        header(a);

        // sort the array and display trace
        sort(a);

        // display the footer
        //footer(a, j);
    }
}
