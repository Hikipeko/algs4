import edu.princeton.cs.algs4.StdOut;

public class Date implements Comparable<Date> {
    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (month == that.month && day == that.day && year == that.year);
    }

    public int compareTo(Date o) {
        if (year > o.year) return 1;
        if (year < o.year) return -1;
        if (month > o.month) return 1;
        if (month < o.month) return -1;
        if (day > o.day) return 1;
        if (day < o.day) return -1;
        return 0;
    }

    public int hashCode() {
        return day + 31 * month + 31 * 12 * year;
    }

    public static void main(String[] args) {
        Date date = new Date("5/21/2021");
        StdOut.print(date.compareTo(new Date("5/22/2021")));
        StdOut.print(date.getClass());
    }
}
