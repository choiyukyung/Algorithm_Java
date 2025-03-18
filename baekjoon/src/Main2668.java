import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//2668
public class Main2668 {
    static int[] arr;
    static ArrayList<Integer> answer;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        arr = new int[n+1];

        for(int i = 1; i<arr.length;i++) {
            arr[i] = scan.nextInt();
        }
        scan.close();

        answer = new ArrayList<>();
        visited = new boolean[n + 1];
        for(int i = 1; i<arr.length;i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        //output
        System.out.println(answer.size());

        Collections.sort(answer);
        for(int i : answer) {
            System.out.println(i);
        }
    }
            
    private static void dfs(int start, int target) {
        if(visited[arr[start]] == false) {
            visited[arr[start]] = true;
            dfs(arr[start], target);
            visited[arr[start]] = false;
        }

        if(arr[start] == target) {
            answer.add(target); // 처음의 target 하나만 지정하면 됨.
            //전체 사이클은 for문으로 각각 저장되니까 어차피 나중에 그 for문 돌 때 저장됨.
        }
    }
}