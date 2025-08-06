import java.io.*;
import java.util.*;

public class Main1707 {
    static int tc, v, e;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        tc = Integer.parseInt(st.nextToken());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            // 인접리스트
            list = new ArrayList<>();
            for (int i = 0; i < v + 1; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u1 = Integer.parseInt(st.nextToken());
                int u2 = Integer.parseInt(st.nextToken());
                // 양방향
                list.get(u1).add(u2);
                list.get(u2).add(u1);
            }

            // 그래프가 하나라고 안했음
            visited = new boolean[v + 1];
            set = new int[v + 1]; // 1, 2 로 set 번호 저장
            boolean answer = true;
            for (int i = 1; i < v + 1; i++) {
                if (visited[i])
                    continue;
                if (!bfs(i)) {
                    answer = false;
                    break;
                }
            }

            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }

    private static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        set[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;

            for (int node : list.get(now)) {
                if (visited[node])
                    continue;
                if (set[node] == set[now]) {
                    return false;
                } else if (set[node] == 0) {
                    set[node] = (set[now] == 1) ? 2 : 1;
                    queue.add(node);
                }
            }

        }
        return true;
    }

}
