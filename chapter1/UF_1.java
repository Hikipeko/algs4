import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class UF_1 {
    private int[] id;
    private int count;
    private int[] sz;
    private double time;

    public double time() {
        return time;
    }

    public UF_1(int n) {
        id = new int[n];
        sz = new int[n];
        count = n;
        time = 0;
        for (int i = 0; i < n; i++) id[i] = i;
        for (int i = 0; i < n; i++) sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int findSlow(int p) {
        return id[p];
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
            time++;
        }
        time++;
        return p;
    }

    public void unionSlow(int p, int q) {
        int pID = findSlow(p);
        int qID = findSlow(q);
        if (pID == qID) return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = pID;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            sz[j] += sz[i];
            id[i] = j;
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        double[] result = new double[10000];
        UF_1 uf = new UF_1(n);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(4, 5);
        StdDraw.setPenRadius(.005);
        for (int i = 1; i < 10000; i++) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            result[i] = uf.time() / i;
        }
        for (int j = 0; j < 10000; j++)
            StdDraw.point(j, result[j]);
        StdOut.println(uf.count() + " components");
        StdOut.println(uf.time() + " times");
    }
}
