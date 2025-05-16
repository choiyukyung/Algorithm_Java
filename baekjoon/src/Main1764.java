import java.io.*;
import java.util.*;

public class Main1764 {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), 1);
        }

        int answer = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if(map.getOrDefault(str, 0)>0) {
                answer++;
                list.add(str);
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(answer);
        System.out.println(sb);

    }

}
