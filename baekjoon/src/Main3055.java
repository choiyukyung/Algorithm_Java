import java.io.*;
import java.util.*;

public class Main3055 {
    static int R, C;
    static char[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] waterTime, goTime;
    static Queue<int[]> waterQ, goQ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        waterTime = new int[R][C];
        goTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(waterTime[i], -1);
            Arrays.fill(goTime[i], -1);
        }

        waterQ = new LinkedList<>();
        goQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            char[] c = st.nextToken().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = c[j];
                if (map[i][j] == '*') {
                    waterQ.add(new int[] { i, j });
                    waterTime[i][j] = 0;
                }
                if (map[i][j] == 'S') {
                    goQ.add(new int[] { i, j });
                    goTime[i][j] = 0;
                }
            }
        }

        bfsWater();
        bfsGo();
    }

    private static void bfsWater() {
        while (!waterQ.isEmpty()) {
            int[] now = waterQ.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == 'X')
                    continue;

                if (waterTime[nx][ny] != -1 || map[nx][ny] == 'D')
                    continue;

                waterTime[nx][ny] = waterTime[now[0]][now[1]] + 1;
                waterQ.add(new int[] { nx, ny });
            }
        }
    }

    public static void bfsGo() {
        while (!goQ.isEmpty()) {
            int[] now = goQ.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    continue;
                }

                if (map[nx][ny] == 'D') {
                    System.out.println(goTime[now[0]][now[1]] + 1);
                    return;
                }

                if (map[nx][ny] == 'X' || goTime[nx][ny] != -1)
                    continue;
                if (waterTime[nx][ny] != -1 && waterTime[nx][ny] <= goTime[now[0]][now[1]] + 1)
                    continue;

                goTime[nx][ny] = goTime[now[0]][now[1]] + 1;
                goQ.add(new int[] { nx, ny });
            }
        }

        System.out.println("KAKTUS");
    }
}
