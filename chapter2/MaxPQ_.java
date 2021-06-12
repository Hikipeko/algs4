// 这是一个二叉堆

import edu.princeton.cs.algs4.StdOut;

public class MaxPQ_<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ_(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ_(Key[] a) {
        pq = (Key[]) new Comparable[65];
        for (int i = 0; i < a.length; i++)
            pq[i + 1] = a[i];
        N = a.length;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Key delMax() {
        Key res = pq[1];
        pq[1] = pq[N--];
        pq[N + 1] = null; //防止对象游离
        sink(1);
        return res;
    }

    public static void main(String[] args) {
        Integer[] ints = new Integer[64];
        for (int i = 0; i < 64; i++)
            ints[i] = 65 - i;
        MaxPQ_ maxpq = new MaxPQ_(ints);
        for (int i = 0; i < 65; i++)
            maxpq.swim(i);
        for (int i = 1; i < 65; i++)
            StdOut.println(maxpq.delMax());
    }
}
