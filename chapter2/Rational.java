import edu.princeton.cs.algs4.StdOut;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int n, int d) {
        numerator = n;
        denominator = d;
    }

    public Rational plus(Rational b) {
        int n = numerator * b.denominator + denominator * b.numerator;
        int d = denominator * b.denominator;
        return (new Rational(n, d)).simplify();
    }

    public Rational minus(Rational b) {
        int n = numerator * b.denominator - denominator * b.numerator;
        int d = denominator * b.denominator;
        return (new Rational(n, d)).simplify();
    }

    public Rational times(Rational b) {
        int n = numerator * b.numerator;
        int d = denominator * b.denominator;
        return (new Rational(n, d)).simplify();
    }

    public Rational devide(Rational b) {
        int n = numerator * b.denominator;
        int d = denominator * b.numerator;
        return (new Rational(n, d)).simplify();
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    private Rational simplify() {
        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }

        int gcd = gcd(Math.abs(numerator), denominator);
        numerator /= gcd;
        denominator /= gcd;
        return this;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Rational r1 = new Rational(4, 6);
        Rational r2 = new Rational(1, 6);
        StdOut.print(r1.minus(r2));
    }
}
