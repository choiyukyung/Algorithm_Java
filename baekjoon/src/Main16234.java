import java.io.*;
import java.util.*;

public class Main16234 {
    static int N, L, R;
    static int[][] arr, set;

    static int[] dx = { -1, 0, 0, 1 };
    static int[] dy = { 0, -1, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            set = new int[N][N];

            int s = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (set[i][j]==0) {
                        bfs(i, j, s);
                        s++;
                    }
                }
            }
            s--;

            if (s == N * N)
                break;

            answer++;
            move(s);
        }

        System.out.println(answer);
    }

    public static void bfs(int sx, int sy, int sg) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { sx, sy });
        set[sx][sy] = sg;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || set[nx][ny]>0)
                    continue;

                int diff = Math.abs(arr[x][y] - arr[nx][ny]);
                if (diff >= L && diff <= R) {
                    queue.add(new int[] { nx, ny });
                    set[nx][ny] = sg;
                }

            }
        }
    }

    public static void move(int s) {
        int[][] setSum = new int[s+1][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                setSum[set[i][j]][0]++;
                setSum[set[i][j]][1] += arr[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = setSum[set[i][j]][1]/setSum[set[i][j]][0];
            }
        }
        
    }

}
