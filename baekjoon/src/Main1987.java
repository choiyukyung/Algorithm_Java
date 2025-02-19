import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1987 {

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int r, c;
    static char[][] arr;
    static boolean[] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        
        for (int i = 0; i<r; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j<c; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        //visited = new ArrayList<>();
        //contains 시간초과
        
        visited = new boolean[26];
        int alphabet = arr[0][0] - 'A';
        visited[alphabet] = true;

        dfs(1, 0, 0);

        System.out.println(answer);
    }

    static void dfs(int depth, int x, int y) {
        answer = Math.max(depth, answer);
        //System.out.println(depth + " " + arr[x][y]);
        
        for(int i = 0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=r || ny>=c) {
                continue;
            }

            int alphabet = arr[nx][ny] - 'A';
            if(!visited[alphabet]) {
                visited[alphabet] = true;
                dfs(depth+1, nx, ny);
                visited[alphabet] = false;
            }
        }
    }
    
}
