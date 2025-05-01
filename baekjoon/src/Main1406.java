import java.io.*;
import java.util.*;

public class Main1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        int cursor = sb.length();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken().charAt(0)) {
                case 'L':
                    cursor = (cursor > 0) ? cursor - 1 : 0;
                    break;
                case 'D':
                    cursor = (cursor < sb.length()) ? cursor + 1 : sb.length();
                    break;
                case 'B':
                    if(cursor>0) sb.deleteCharAt(cursor-1);
                    cursor = (cursor > 0) ? cursor - 1 : 0;
                    break;
                case 'P':
                    sb.insert(cursor, st.nextToken());
                    cursor = (cursor < sb.length()) ? cursor + 1 : sb.length();
                    break;
            }
            //System.out.println(i+1 + " : " + sb.toString() + " " + cursor);
        }

        System.out.println(sb.toString());
    }
}
