import java.util.Scanner;
import java.util.Stack;

public class Main10799 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        scan.close();

        char[] arr = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.add(arr[i]);
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    if (input.charAt(i - 1) == '(')
                        answer += stack.size();
                    else {
                        answer++;
                    }
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
