import java.io.*;
import java.util.*;

public class Main1937 {

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int n;
    static int[][] arr;
    static int answer = 0;
    static int[][] memoi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memoi = new int[n][n]; //도착지[n-1][n-1]까지 갈 수 있는 최장 경로
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memoi[i][j] = -1; //미방문(visited 역할도)
            }
        }

        dfs(0, 0);

        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int x, int y) {

        if (memoi[x][y] != -1) {
            return memoi[x][y];
        }

        memoi[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }

            if (arr[nx][ny] > arr[x][y]) {
                memoi[x][y] = Math.max(memoi[x][y], dfs(nx, ny) + 1);
            }
        }

        return memoi[x][y];
    }

}
