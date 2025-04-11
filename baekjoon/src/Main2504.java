import java.util.Scanner;
import java.util.Stack;

public class Main2504 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        scan.close();

        char[] arr = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int now = 1;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.add(arr[i]);
                now *= 2;
            } else if (arr[i] == '[') {
                stack.add(arr[i]);
                now *= 3;
            } else if (arr[i] == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    if (input.charAt(i - 1) == '(')
                        answer += now;
                    now /= 2;
                } else {
                    System.out.println(0);
                    return;
                }
            } else if (arr[i] == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                    if (input.charAt(i - 1) == '[')
                        answer += now;
                    now /= 3;
                } else {
                    System.out.println(0);
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
    }
}
