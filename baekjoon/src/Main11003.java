import java.io.*;
import java.util.*;

public class Main11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] list = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        int[] D = new int[N];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            // 덱 뒤에서부터 현재값보다 큰 값 제거
            while (!deque.isEmpty() && deque.getLast()[1] >= list[i]) {
                deque.removeLast();
            }

            // 현재(가장 최신)값 추가
            deque.addLast(new int[] { i, list[i] });

            // 범위 벗어난 값 제거
            if (deque.getFirst()[0] <= i - L) {
                deque.removeFirst();
            }

            // 구간 최솟값 저장
            D[i] = deque.getFirst()[1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(D[i] + " ");
        }
        System.out.println(sb);
    }
}
