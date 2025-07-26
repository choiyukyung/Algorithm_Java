import java.io.*;
import java.util.*;

public class Main1520 {

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int m, n;
    static int[][] arr;
    static int answer = 0;
    static int[][] memoi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memoi = new int[m][n]; //그 위치에서 도착지[m-1][n-1]까지 갈 수 있는 경로 수
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memoi[i][j] = -1; //미방문(visited 역할도)
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        if (memoi[x][y] != -1) {
            return memoi[x][y];
        }

        memoi[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }

            if (arr[nx][ny] < arr[x][y]) {
                memoi[x][y] += dfs(nx, ny);
            }
        }

        return memoi[x][y];
    }

}
