import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class BubbleSort {
    public static void main(String[] args) {
        int N = 50;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(2 * N);
        for (int j = N; j >= 0; j--) {
            for (int i = 0; i < N; i++) {
                double x = 1.0 * i / N;
                double y = a[i] / (4.0 * N);
                double rw = 0.5 / N;
                double rh = a[i] / (4.0 * N);
                StdDraw.filledRectangle(x, y, rw, rh);
            }
            bubble(a, j);
            StdDraw.show(20);
            StdDraw.clear();
        }

    }

    public static void bubble(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }
}
