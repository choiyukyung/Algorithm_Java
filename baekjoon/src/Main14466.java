import java.io.*;
import java.util.*;

public class Main14466 {
    static int n, k, r;
    static boolean[][][] road;
    static boolean[][][] canCow;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        road = new boolean[n + 1][n + 1][4];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                if (x1 == x2 + dx[j] && y1 == y2 + dy[j]) {
                    road[x2][y2][j] = true;
                }
                if (x2 == x1 + dx[j] && y2 == y1 + dy[j]) {
                    road[x1][y1][j] = true;
                }
            }
        }

        int[][] cow = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            cow[i][0] = Integer.parseInt(st.nextToken());
            cow[i][1] = Integer.parseInt(st.nextToken());
        }

        // 소들이 갈수 있는 곳?
        canCow = new boolean[n + 1][n + 1][k];
        for (int i = 0; i < k; i++) {
            bfs(i, cow[i][0], cow[i][1]);
        }

        int answer = 0;

        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                // 소 i,j가 동시에 같은곳에 있지 못하면 answer++;
                boolean meet = false;
                for (int x = 1; x <= n; x++) {
                    for (int y = 1; y <= n; y++) {
                        if (canCow[x][y][i] && canCow[x][y][j]) {
                            meet = true;
                            break;
                        }
                    }
                    if (meet)
                        break;
                }
                if (!meet)
                    answer++;
            }
        }

        System.out.println(answer);

    }

    static void bfs(int cowNum, int sx, int sy) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sx, sy });
        canCow[sx][sy][cowNum] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                // 도로는 못 지나가게
                if (road[now[0]][now[1]][i])
                    continue;

                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx <= 0 || ny <= 0 || nx > n || ny > n || canCow[nx][ny][cowNum])
                    continue;

                q.add(new int[] { nx, ny });
                canCow[nx][ny][cowNum] = true;
            }

        }

    }

}
