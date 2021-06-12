import edu.princeton.cs.algs4.StdDraw;

public class Test {
    public static void main(String[] args) {
        int n = 10;
        StdDraw.setCanvasSize(30 * (n + 3), 30 * (n + 3));
        StdDraw.setXscale(-3, n + 1);
        StdDraw.setYscale(n + 1, -3);
    }
}
