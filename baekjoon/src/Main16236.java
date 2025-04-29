import java.io.*;
import java.util.*;

public class Main16236 {
    static int N, sx, sy;
    static int[][] arr;
    static int ssize = 2;

    static int[] dx = { -1, 0, 0, 1 };
    static int[] dy = { 0, -1, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        sx = 0;
        sy = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    sx = i;
                    sy = j;
                    arr[i][j] = 0; // 이 줄이 꼭 필요. 상어를 물고기로 인식하지 않으려면
                }
            }
        }

        int answer = 0;
        int eat = 0;
        while (true) {
            Fish dest = bfs();
            if (dest == null)
                break;

            // 단순 이동
            sx = dest.x;
            sy = dest.y;
            answer += dest.dist;

            eat++;
            if (eat == ssize) {
                ssize++;
                eat = 0;
            }

            arr[sx][sy] = 0;
        }

        System.out.println(answer);
    }

    public static Fish bfs() {
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Fish> pq = new PriorityQueue<>();

        queue.add(new int[] { sx, sy, 0 });
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
                    continue;

                if (arr[nx][ny] <= ssize) {
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny, dist + 1 });
                    if (arr[nx][ny] > 0 && arr[nx][ny] < ssize) {
                        pq.add(new Fish(nx, ny, dist + 1));
                    }
                }

            }
        }
        return pq.isEmpty()?null:pq.poll();
    }

    public static class Fish implements Comparable<Fish> {
        int x, y, dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish other) {
            if (this.dist != other.dist)
                return this.dist - other.dist;
            if (this.x != other.x)
                return this.x - other.x;
            return this.y - other.y;
        }
    }

}
