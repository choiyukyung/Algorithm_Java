import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i<=N;i++) {
            list.add(i);
        }

        int now = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        while(!list.isEmpty()) {
            now += K;
            while(now>list.size()){
                now -= list.size();
            }
            answer.add(list.get(now-1));
            list.remove(now-1);
            now--;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 0; i<N-1;i++) {
            sb.append(answer.get(i));
            sb.append(", ");
        }
        sb.append(answer.get(N-1));
        sb.append(">");
        System.out.println(sb);
    }
}
