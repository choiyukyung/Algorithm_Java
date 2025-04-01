import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] { 0, arr[0] });

        int[] answer = new int[N];
        Arrays.fill(answer, -1);

        for (int i = 0; i < N - 1; i++) {
            while (!stack.isEmpty() && arr[i + 1] > stack.peek()[1]) {
                int[] p = stack.pop();
                answer[p[0]] = arr[i + 1];
            }
            stack.add(new int[] { i + 1, arr[i + 1] });
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }
}
