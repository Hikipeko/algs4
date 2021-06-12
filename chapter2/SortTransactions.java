import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SortTransactions {
    public static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
        while (StdIn.hasNextLine()) {
            String str = StdIn.readLine();
            queue.enqueue(new Transaction(str));
        }
        int n = queue.size();
        Transaction[] result = new Transaction[n];
        for (int i = 0; i < n; i++)
            result[i] = queue.dequeue();
        return result;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        Arrays.sort(transactions);
        for (int i = 0; i < transactions.length; i++)
            StdOut.println(transactions[i]);
    }
}
