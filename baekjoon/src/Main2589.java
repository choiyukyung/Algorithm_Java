import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2589 {
    static int N,M;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken().toString();
            for (int j = 0; j<M;j++) {
                if(str.charAt(j)=='L'){ // 육지는 1
                    map[i][j] = 1;
                } else { // 바다는 2
                    map[i][j] = 2;
                }
            }
        }

        int answer = 0;

        // N*M = 2500이니까 bf 가능
        for (int i = 0; i<N;i++) {
            for (int j = 0; j<M;j++) {
                if(map[i][j] == 1) {
                    answer = Math.max(answer, bfs(i,j));
                }
                
            }
        }

        
        System.out.println(answer);
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,y,0});
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        int[] now = {0,0,0};
        while(!queue.isEmpty()) {
            now = queue.poll();

            for(int i = 0; i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny] != 1 || visited[nx][ny]) continue;

                queue.add(new int[] {nx, ny, now[2]+1});
                visited[nx][ny] = true;
            }

        }

        return now[2];
    }
}
