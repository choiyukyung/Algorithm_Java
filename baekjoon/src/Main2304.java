
import java.util.Scanner;

public class Main2304 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[1001];

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            int index = scan.nextInt();
            arr[index] = scan.nextInt();
            if(max < arr[index]) {
                max = arr[index];
                maxIndex = index;
            }
        }

        int answer = 0;
        //다각형 면적 계산 /
        int big = 0;
        for (int i = 0; i <= maxIndex; i++) {
            big = Math.max(big, arr[i]);
            answer += big;
        }
        

        //면적 \
        big = 0;
        for (int i = arr.length - 1; i > maxIndex; i--) {
            big = Math.max(big, arr[i]);
            answer += big;
        }

        System.out.println(answer);

    }
}
