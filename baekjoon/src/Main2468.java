import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;;

public class Main2468 {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        arr = new int[N][N];
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int answer = 0;
        int safeNow = 0;
        for (int i = min; i<=max; i++) {
            visited = new boolean[N][N];
            safeNow = 0;
            for (int x = 0; x<N; x++) {
                for (int y = 0; y<N; y++) {
                    if(arr[x][y]>=i && visited[x][y] == false){
                        safeNow++;
                        bfs(x,y,i);
                    }
                }
            }
            answer = Math.max(answer, safeNow);
        }
        System.out.println(answer);
    }

    static void bfs(int x, int y, int i) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];

            for(int d = 0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=N || arr[nx][ny]<i || visited[nx][ny]){
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new int[] {nx,ny});
            }

        }
    }
}
