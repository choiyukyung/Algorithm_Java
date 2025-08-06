import java.io.*;
import java.util.*;

public class Main11657 {
    static int v, m;
    static List<Edge> edgeList;
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(a, b, c));
        }

        bellmanFord(1);
    }

    public static void bellmanFord(int start) {
        //int[]는 출력 초과: 500개 vertex, 6,000개의 edge가 -10,000 일 경우 최솟값 계산이기에 -30억(백준 질문 게시판에서 찾음)
        long[] distance = new long[v+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        
        for (int i = 1; i < v; i++) {
            boolean update = false;
            for (Edge edge : edgeList) {
                if (distance[edge.s] != INF && distance[edge.s] + edge.w < distance[edge.e]) {
                    distance[edge.e] = distance[edge.s] + edge.w;
                    update = true;
                }
            }

            if (!update)
                break;
        }

        for (Edge edge : edgeList) {
            if (distance[edge.s] != INF && distance[edge.s] + edge.w < distance[edge.e]) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 2; i < v+1; i++) {
            if (distance[i] == INF)
                System.out.println(-1);
            else
                System.out.println(distance[i]);
        }
    }

    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

}