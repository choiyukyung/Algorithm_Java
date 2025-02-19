import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        // random case test
        // str = test(n);
        // System.out.println(str);

        Deque<Character> stack = new LinkedList<>();

        for (char num : str.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() < num) {
                stack.pollLast();
                k--;
            }
            stack.addLast(num);
        }

        while (k > 0) {
            stack.pollLast();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (char num : stack) {
            sb.append(num);
        }

        System.out.println(sb);
    }

    static String test(int n) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // 첫 번째 숫자는 1~9 사이로 설정
        sb.append(rand.nextInt(9) + 1);
        
        // 나머지 자리 숫자는 0~9 랜덤으로 생성
        for (int i = 1; i < n; i++) {
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }
}
