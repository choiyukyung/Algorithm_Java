import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n];
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] { n - 1, arr[n - 1] });
        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()[1] <= arr[i]) {
                int[] p = stack.pop();
                answer[p[0]] = i + 1;
            }
            stack.add(new int[] { i, arr[i] });
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);
    }
}
