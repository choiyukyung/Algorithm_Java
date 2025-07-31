import java.io.*;
import java.util.*;

public class Main2636 {
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        int cheeze = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    cheeze++;
                }
            }

        }

        int time = 0;
        int last = 0;
        while (cheeze > 0) {
            visited = new boolean[n][m];
            // 치즈 찾아서 -1 표시
            bfs();
            // 표시해둔 치즈 삭제
            last = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == -1) {
                        arr[i][j] = 0;
                        last++;
                    }
                }
            }
            // 이번 턴에 삭제한 치즈 수 last
            // 남은 치즈 cheeze가 0이 될때까지
            cheeze -= last;
            time++;
        }

        System.out.println(time);
        System.out.println(last);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny])
                    continue;

                if (arr[nx][ny] == 0) {
                    queue.add(new int[] { nx, ny });
                } else if (arr[nx][ny] == 1) {
                    arr[nx][ny] = -1;
                }
                visited[nx][ny] = true;
            }

        }
    }
}
