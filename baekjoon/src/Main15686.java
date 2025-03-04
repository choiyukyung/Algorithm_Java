import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15686 {
    static int n,m, answer;
    static int[][] city;
    static ArrayList<int[]> cklist;
    static int[] comb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n+1][n+1];
        cklist = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j]==2) {
                    cklist.add(new int[]{i,j});
                }
            }
        }

        comb = new int[m];
        answer = Integer.MAX_VALUE;
        dfs(0, 0);

        System.out.println(answer);

    }

    // 치킨 집 조합
    static void dfs(int depth, int index) {
        if(depth == m) {
            int sum = sum();
            answer = Math.min(sum, answer);
            return;
        }

        for(int i = index; i<cklist.size(); i++) {
            comb[depth] = i;
            dfs(depth+1, i+1);
        }
    }

    static int sum() {
        int[][] dist = new int[n+1][n+1];
        //초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(city[i][j] == 1) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        //치킨집 하나씩 돌기기
        for(int c : comb) {
            // 현재 치킨집
            int[] ch = cklist.get(c);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(city[i][j] == 1) {
                        dist[i][j] = Math.min(dist[i][j], Math.abs(ch[0] - i) +  Math.abs(ch[1] - j));
                    }
                }
            }
        }

        // 합 구하기
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(city[i][j] == 1) {
                    sum += dist[i][j];
                }
            }
        }

        return sum;
    }
}
