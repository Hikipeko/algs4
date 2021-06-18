import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

//基于二叉查找树的符号表
public class BST_<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // size of subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public BST_() {
        root = null;
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


    public Value getOld(Key key) {
        return get(root, key).val;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //floor(), ceiling(), select(), rank(), delete(), deleteMin(), deleteMax(), keys();
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        while (x.left != null) x = x.left;
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != null) x = x.right;
        return x;
    }

    public Key floor(Key key) {
        return floor(root, key).key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, q, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
        if (cmphi > 0) keys(x.right, q, lo, hi);
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return 0;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split("\\s+");
        int n = keys.length;

        BST_<String, Integer> st = new BST_<String, Integer>();
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();


        // print keys in order using keys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();


        // print keys in order using select
        StdOut.println("Testing select");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            StdOut.println(i + " " + st.select(i));
        StdOut.println();

        // test rank, floor, ceiling
        StdOut.println("key rank floor ceil");
        StdOut.println("-------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
        }
        StdOut.println();


        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        /*
        StdOut.println("After deleting the remaining keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
*/
        StdOut.println("After adding back N keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
    }
}


