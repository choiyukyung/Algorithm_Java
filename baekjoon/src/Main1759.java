import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1759 {
    static int L, C;
    static char[] arr;
    static StringBuilder sb;
    static ArrayList<Character> vowels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new char[C];
        vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        sb = new StringBuilder();
        dfs(0, 0, "", 0);

        System.out.println(sb);
    }

    public static void dfs(int depth, int start, String str, int vowelCnt) {
        if (depth == L) {
            if (vowelCnt >= 1 && L - vowelCnt >= 2) {
                sb.append(str).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            dfs(depth + 1, i + 1, str + arr[i], vowels.contains(arr[i]) ? vowelCnt + 1 : vowelCnt);
        }
    }
}
