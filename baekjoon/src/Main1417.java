import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main1417 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        scan.close();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i < n; i++) {
            pq.add(arr[i]);
        }

        int answer = 0;
        while (pq.peek() >= arr[0]) {
            answer++;
            pq.add(pq.poll() - 1);
            arr[0]++;
        }
        System.out.println(answer);
    }
}
