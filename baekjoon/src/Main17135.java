import java.io.*;
import java.util.*;

public class Main17135 {
    static int n, m, d, answer;
    static int[][] map;
    static boolean[] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;

        comb = new boolean[m];
        combination(0, 0);

        System.out.println(answer);

    }

    private static void combination(int depth, int start) {
        if (depth == 3) {
            boolean[] copy = Arrays.copyOf(comb, comb.length);
            castleDefence(copy);
            return;
        }
        for (int i = start; i < m; i++) {
            comb[i] = true;
            combination(depth + 1, i + 1);
            comb[i] = false;
        }
    }

    private static void castleDefence(boolean[] candi) {

        int answerCandi = 0;

        int[][] map_copy = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map_copy[i][j] = map[i][j];
            }
        }

        int[] arrow = new int[3];
        int idx = 0;
        for (int i = 0; i < candi.length; i++) {
            if (candi[i]) {
                arrow[idx] = i;
                idx++;
            }
        }

        for (int i = 0; i <= n; i++) {

            // 궁수 공격
            answerCandi += arrowAttack(arrow, map_copy);

            // 적 하강
            enemyDown(map_copy);
        }

        answer = Math.max(answer, answerCandi);

    }

    private static void enemyDown(int[][] map_copy) {
        for (int i = 0; i < m; i++) {
            map_copy[n - 1][i] = 0;
            for (int j = n - 2; j >= 0; j--) {
                if (map_copy[j][i] == 1) {
                    map_copy[j][i] = 0;
                    map_copy[j + 1][i] = 1;
                }
            }
        }
    }

    private static int arrowAttack(int[] arrow, int[][] map_copy) {

        int kill = 0;

        // 찾기
        int[][] enemyLoca = new int[3][2];
        int[] enemyDist = new int[3];

        for (int a = 0; a < 3; a++) {

            int distMin = Integer.MAX_VALUE;

            for (int j = 0; j < m; j++) {
                for (int i = 0; i < n; i++) {

                    if (map_copy[i][j] == 1) {
                        int dist = (n - i) + Math.abs(j - arrow[a]);
                        if (dist <= d && dist < distMin) {
                            distMin = dist;
                            enemyLoca[a][0] = i;
                            enemyLoca[a][1] = j;
                        }
                    }
                }
            }

            enemyDist[a] = distMin;
        }

        for (int a = 0; a < 3; a++) {
            // 제거
            if (enemyDist[a] <= d && map_copy[enemyLoca[a][0]][enemyLoca[a][1]] == 1) {
                // System.out.println(arrow[a] + " killed " + enemyLoca[a][0] + " " +
                // enemyLoca[a][1]);
                kill++;
                map_copy[enemyLoca[a][0]][enemyLoca[a][1]] = 0;
            }

        }

        return kill;

    }

}
