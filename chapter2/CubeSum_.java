/* *****************************************************************************
 *  Name:    Ada Lovelace
 *  NetID:   alovelace
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class CubeSum_ implements Comparable<CubeSum_> {
    private final int i;
    private final int j;
    private final int sum;

    public CubeSum_(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = i * i * i + j * j * j;
    }

    public int compareTo(CubeSum_ that) {
        if (this.sum < that.sum) return -1;
        else if (this.sum > that.sum) return +1;
        else return 0;
    }

    public String toString() {
        return i + "^3 + " + j + "^3 = " + sum;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        MinPQ<CubeSum_> maxpq = new MinPQ<CubeSum_>();
        for (int i = 0; i <= n; i++)
            maxpq.insert(new CubeSum_(i, i));
        while (!maxpq.isEmpty()) {
            CubeSum_ s = maxpq.delMin();
            if (s.j < n)
                maxpq.insert(new CubeSum_(s.i, s.j + 1));
            StdOut.println(s);
        }
    }
}
