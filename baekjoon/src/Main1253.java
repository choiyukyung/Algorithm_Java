import java.io.*;
import java.util.*;

public class Main1253 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int count0 = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
            if (num == 0)
                count0++;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            if (list.get(i) == 0)
                continue;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) != 0) { // 0이 들어가는 케이스는 모두 지워버리고 나머지 nC2 더하기
                    set.add(list.get(i) + list.get(j));
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!set.isEmpty() && set.contains(list.get(i))) { // 0 아예 안 고려한 버전
                answer++;
            } else if (list.get(i) == 0 && count0 > 2) { // 반례(0이 3개 이상이어서 0 자기자신을 만들 수 있는 케이스)
                answer++;
            } else if (list.get(i) != 0 && count0 > 0 && Collections.frequency(list, list.get(i)) > 1) {
                answer++; // 반례(0+x=x에서 0이 존재하고, x 개수가 2개 이상이어서 x를 만들 수 있는 케이스)
            }
        }

        System.out.println(answer);

    }

}
