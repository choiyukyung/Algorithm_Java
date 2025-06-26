import java.io.*;
import java.util.*;

public class Main4386 {
    static int N;
    static ArrayList<double[]> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        double[][] star = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            star[i][0] = Double.parseDouble(st.nextToken());
            star[i][1] = Double.parseDouble(st.nextToken());
        }

        edgeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dx = star[i][0] - star[j][0];
                double dy = star[i][1] - star[j][1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edgeList.add(new double[] { i, j, dist });
            }
        }

        edgeList.sort((a, b) -> Double.compare(a[2], b[2]));

        // 유니온 파인드 리스트 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        double answer = 0;

        // MST 크루스칼
        for (double[] edge : edgeList) {
            int from = (int) edge[0];
            int to = (int) edge[1];
            if (find(from) != find(to)) {
                union(from, to);
                answer += edge[2];
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 여기서 경로가 지저분해져도 find에서 압축
        }
    }
}
