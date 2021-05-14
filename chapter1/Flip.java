public class Flip {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Counter head = new Counter("head", 2 << 12);
        Counter tail = new Counter("tail", 2 << 12);
        for (int i = 0; i < T; i++)
            if (StdRandom.bernoulli(0.5))
                head.increment();
            else tail.increment();
        StdOut.println(head);
        StdOut.println(tail);
        int d = head.value() - tail.value();
        StdOut.println(d);
    }
}
