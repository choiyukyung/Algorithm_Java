import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main6593 {
    static int L, R, C;
    static char[][][] building;
    static int[] dl = {1,-1,0,0,0,0};
    static int[] dr = {0,0,1,-1,0,0};
    static int[] dc = {0,0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0)
                return;

            building = new char[L][R][C];

            int[] start = new int[3];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int l = 0; l < C; l++) {
                        building[i][j][l] = str.charAt(l);
                        if (building[i][j][l] == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = l;
                        }
                    }
                }
                br.readLine();
            }

            int answer = bfs(start);

            if (answer == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + answer + " minute(s).");
            }
        }
    }

    static int bfs(int[] start) {
        boolean[][][] visited = new boolean[L][R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i = 0; i<dl.length; i++) {
                int nl = now[0] + dl[i];
                int nr = now[1] + dr[i];
                int nc = now[2] + dc[i];

                if(nl<0 || nl>=L || nr<0 || nr>=R || nc<0 || nc>=C 
                || visited[nl][nr][nc] || building[nl][nr][nc] == '#') continue;
                
                visited[nl][nr][nc] = true;

                if(building[nl][nr][nc] == 'E') {
                    return now[3] + 1;
                }

                queue.add(new int[] {nl, nr, nc, now[3] + 1});

            }
        }

        return -1;
    }
}
