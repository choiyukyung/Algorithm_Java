import java.io.*;
import java.util.*;

public class Main13460 {
    static int n, m;
    static char[][] map;

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        int[] red = new int[2];
        int[] blue = new int[2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                } else if (map[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }

        bfs(red, blue);

        System.out.println(answer);

    }

    static void bfs(int[] red, int[] blue) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { red[0], red[1], blue[0], blue[1], 0 });
        boolean[][][][] visited = new boolean[n][m][n][m]; // 이래야 찾을때 O(n)
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int depth = now[4] + 1;
            if (depth > 10) {
                answer = -1;
                return;
            }

            // 이제 벽이 나올때까지 한칸씩 굴리기
            // 굴리다가 중간에 구멍이 나오면 체크
            // blue를 먼저 굴려야 실패 확인이 빠름. blue 먼저 굴리다가 구멍에 걸리면 bfs에 아무것도 넣지 않기
            // blue 무사히 넘어가고 red에서 구멍이 걸리는 케이스 확인 시 answer 후보 처리
            // blue 먼저 굴리다가 red와 부딪힐거 같으면 blue 멈추고, red 다 굴린 후 다시 blue 굴리기
            // visited나 bfs는 다 이동한 후 넣으면 됨..
            for (int i = 0; i < 4; i++) {
                int rx = now[0];
                int ry = now[1];
                int bx = now[2];
                int by = now[3];

                // blue 굴리기
                boolean fail = false;
                boolean hit = false; // 부딪
                while (true) {
                    bx += dx[i];
                    by += dy[i];
                    if (map[bx][by] == 'O') {
                        // 블루가 구멍에 들어감! 실패
                        fail = true;
                        break;
                    } else if (bx == rx && by == ry) {
                        // 블루의 경로에 레드가 있음(멈추고 레드먼저)
                        hit = true;
                        bx -= dx[i];
                        by -= dy[i];
                        break;
                    } else if (map[bx][by] == '#') {
                        bx -= dx[i];
                        by -= dy[i];
                        break;
                    }
                }

                if (!fail) {

                    // red 굴리기
                    while (true) {
                        rx += dx[i];
                        ry += dy[i];
                        if (map[rx][ry] == 'O') {
                            // 레드가 구멍에 들어감!
                            // 블루가 뒤에서 따라오고 있지 않으면 성공!
                            if (!hit) {
                                answer = depth; // 최단 bfs
                                return;
                            } else { // 블루가 뒤에서 따라오고 있다면 이 경로는 실패
                                fail = true;
                                break;
                            }
                        } else if (bx == rx && by == ry) {
                            // 레드의 경로에 블루가 있음(블루가 먼저 이동했기 때문에 벽처럼 생각)
                            rx -= dx[i];
                            ry -= dy[i];
                            break;
                        } else if (map[rx][ry] == '#') {
                            rx -= dx[i];
                            ry -= dy[i];
                            break;
                        }
                    }
                }

                if (!fail && hit) { // 처음에 블루 먼저 돌렸다가 부딪힌 경우
                    while (true) {
                        bx += dx[i];
                        by += dy[i];
                        if (map[bx][by] == 'O') {
                            // 블루가 구멍에 들어감! 실패
                            fail = true;
                            break;
                        } else if (bx == rx && by == ry) {
                            // 블루의 경로에 레드가 있음(이젠 벽처럼 생각)
                            bx -= dx[i];
                            by -= dy[i];
                            break;
                        } else if (map[bx][by] == '#') {
                            bx -= dx[i];
                            by -= dy[i];
                            break;
                        }
                    }
                }

                if (!fail && !visited[rx][ry][bx][by]) {
                    queue.add(new int[] { rx, ry, bx, by, depth });
                    visited[rx][ry][bx][by] = true;
                }
            }

        }
    }

}
