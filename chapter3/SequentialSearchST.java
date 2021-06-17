public class SequentialSearchST<Key, Value> {
    private Node first;
    private int n;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {
        first = null;
        n = 0;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) return x.val;
        return null; // miss
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
        n++;
    }


    public void delete(Key key) {
        if (n == 0) return;
        if (first.key == key) {
            first = first.next;
            n--;
        }
        if (n == 1) return;
        for (Node x = first; x.next != null; x = x.next) {
            if (x.next.key.equals(key)) {
                Node temp = x.next.next;
                x.next = null;
                x.next = temp;
                n--;
                return;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) == null;
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    Iterable<Key> keys() {
        return null;
    }

    public static void main(String[] args) {

    }
}
