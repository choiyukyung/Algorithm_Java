import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1261 {
    static int N, M;
    static int[][] maze;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        maze = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(str.substring(j, j+1));
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        int[][] breakMin = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                breakMin[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0, 0 });
        breakMin[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;

                if(maze[nx][ny] + now[2] >= breakMin[nx][ny]) continue; //방문체크
                //새롭게 방문할 때 기존에 방문했을 때보다 벽 개수를 적게 부수고 방문할때만 허용

                breakMin[nx][ny] = maze[nx][ny] + now[2];
                queue.add(new int[] { nx, ny, maze[nx][ny] + now[2] });

            }
        }

        return breakMin[N-1][M-1];
    }
}
