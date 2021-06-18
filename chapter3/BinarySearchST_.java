import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST_<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST_(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    //如果key存在，返回key的位置；若不存在，则返回表中小于它的键的数量（放入表后所在位置）
    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (hi + lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.equals(keys[i])) return keys[i];
        if (i == 0) return null;
        else return keys[i - 1];
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if (i == N || key.compareTo(keys[i]) != 0) return;
        for (int j = i; j < N - 1; j++) {
            vals[j] = vals[j + 1];
            keys[j] = keys[j + 1];
        }
        N--;
        keys[N] = null;  // to avoid loitering
        vals[N] = null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return q;
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }


    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split("\\s+");
        int n = keys.length;

        BinarySearchST_<String, Integer> st = new BinarySearchST_<String, Integer>(20);
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

        // test range search and range count
        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to = { "Z", "A", "X", "Z", "G", "L" };
        StdOut.println("range search");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
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
