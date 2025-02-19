import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j = 0; j<str.length(); j++) {
                char alphabet = str.charAt(j);
                int count = (int) Math.pow(10, str.length()-j-1);
                map.put(alphabet, map.getOrDefault(alphabet, 0) + count); 
            }

        }

        ArrayList<Integer> val = new ArrayList<>(map.values());
        Collections.sort(val);
        Collections.reverse(val);

        int answer = 0;
        int num = 9;
        for(int i : val) {
            answer += i * num;
            num--;
        }

        System.out.println(answer);
    }
}
