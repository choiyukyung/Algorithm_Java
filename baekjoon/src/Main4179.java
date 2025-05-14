import java.io.*;
import java.util.*;

public class Main4179 {
    static int R, C;
    static char[][] map;
    static int[][] fireT, jihunT;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Pair> fireQ = new LinkedList<>();
    static Queue<Pair> jihunQ = new LinkedList<>();

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireT = new int[R][C];
        jihunT = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(fireT[i], -1);
            Arrays.fill(jihunT[i], -1);
        }

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'F') {
                    fireQ.add(new Pair(i, j));
                    fireT[i][j] = 0;
                }
                if (map[i][j] == 'J') {
                    jihunQ.add(new Pair(i, j));
                    jihunT[i][j] = 0;
                }
            }
        }
        fireBfs();
        jihunBfs();

    }

    public static void fireBfs() {
        while (!fireQ.isEmpty()) {
            Pair cur = fireQ.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (map[nx][ny] == '#' || fireT[nx][ny] != -1) continue;
                fireT[nx][ny] = fireT[cur.x][cur.y] + 1;
                fireQ.add(new Pair(nx, ny));
            }
        }

    }


    public static void jihunBfs() {
        while (!jihunQ.isEmpty()) {
            Pair cur = jihunQ.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    System.out.println(jihunT[cur.x][cur.y] + 1);
                    return;
                }

                if (map[nx][ny] == '#' || jihunT[nx][ny] != -1) continue;
                if (fireT[nx][ny] != -1 && fireT[nx][ny] <= jihunT[cur.x][cur.y] + 1) continue;

                jihunT[nx][ny] = jihunT[cur.x][cur.y] + 1;
                jihunQ.add(new Pair(nx, ny));
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
