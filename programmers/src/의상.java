import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i<clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        return comb(map);
    }
    
    static int comb(HashMap<String, Integer> map) {
        int count = 1;
        for(int i : map.values()) {
            count *= (i+1);
        }
        return count - 1;
    }
}