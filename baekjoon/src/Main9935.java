import java.util.Scanner;
import java.util.Stack;

public class Main9935 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        String bomb = scan.next();
        scan.close();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));

            boolean find = false;
            if (!stack.isEmpty() && stack.peek() == bomb.charAt(bomb.length() - 1)) {
                find = true;
                for (int j = 0; j < bomb.length(); j++) {
                    int stackIdx = stack.size() - bomb.length() + j;
                    if (stackIdx < 0 || stack.get(stackIdx) != bomb.charAt(j)) {
                        find = false;
                    }

                }
            }

            if (find) {
                for (int j = 0; j < bomb.length(); j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }

        if (sb.toString() == "") {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }
    }

}
