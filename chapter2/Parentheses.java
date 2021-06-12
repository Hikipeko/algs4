import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Parentheses {
    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' ) stack.push('(' );
            if (ch == '[' ) stack.push('[' );
            if (ch == '{' ) stack.push('{' );
            if (ch == ')' ) return !(stack.isEmpty() || stack.pop() != '(' );
            if (ch == ']' ) return !(stack.isEmpty() || stack.pop() != ']' );
            if (ch == '}' ) return !(stack.isEmpty() || stack.pop() != '}' );
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        In in = new In();
        String s = in.readAll().trim();
        StdOut.println(isBalanced(s));
    }
}
