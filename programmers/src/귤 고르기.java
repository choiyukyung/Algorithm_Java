import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int size : tangerine) {
            map.put(size, map.getOrDefault(size, 0) + 1);
        }
        
        //서로 다른 종류의 수만 구하면 됨. 어떤 건지는 상관없음
        ArrayList<Integer> counts = new ArrayList<>(map.values());
        counts.sort(Collections.reverseOrder());
        
        int type = 0, total = 0;
        for (int count : counts) {
            total += count;
            type++;
            if (total >= k) {
                break;
            }
        }
        
        return type;
        
    }
}