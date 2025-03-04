import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503 {
    static int n,m, answer;
    static int[][] place;
    static int[] dx = {-1,0,1,0}; //북동남서
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int rx = Integer.parseInt(st.nextToken());
        int ry = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        place = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                place[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //1벽, 0청소X, -1청소O
        simulation(rx, ry, d);

        System.out.println(answer);

    }

    static void simulation(int x, int y, int d) {

        //1
        if(place[x][y] == 0) {
            place[x][y] = -1;
            answer++;
        }
        
        //3
        for(int i = 0; i<4; i++) { // 반시계 90도부터***
            d = (d+3)%4;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
            
            if(place[nx][ny] == 0) {
                simulation(nx, ny, d);

                return;
            }
        }
        //반시계 4번 돌면 정면으로 돌아옴

        //2
        int nx = x + dx[(d+2)%4];
        int ny = y + dy[(d+2)%4];
        if(nx<0 || ny<0 || nx>=n || ny>=m || place[nx][ny] == 1) {
            return;
        } else {
            simulation(nx, ny, d); // 후진은 방향 그대로
        }
        

    }
}
