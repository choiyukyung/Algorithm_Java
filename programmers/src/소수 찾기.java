import java.util.*;

class Solution {
    
    static boolean[] primeCheck;
    static ArrayList<Integer> prime;

    static void eratos(int s) {
        primeCheck = new boolean[s + 1];
        prime = new ArrayList<>();

        Arrays.fill(primeCheck, true);
        primeCheck[0] = primeCheck[1] = false;

        for (int i = 2; i * i <= s; i++) {
            if (primeCheck[i]) {
                for (int j = i * i; j <= s; j += i) {
                    primeCheck[j] = false;
                }
            }
        }

        for (int i = 2; i <= s; i++) {
            if (primeCheck[i]) prime.add(i);
        }
    }
    
    public int solution(int n) {
        eratos(n);
        return prime.size();
    }
}