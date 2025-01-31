import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        
        HashSet<Integer> set = new HashSet<>();
        int s = elements.length;
        
        //구간합 -> 합배열을 미리 구하면 실제로 더할때 O(1)의 시간 복잡도
        //원형 배열이므로 길이가 2배인 합 배열을 구한다.
        int[] sum = new int[s*2];
        sum[0] = elements[0];
        
        for(int i = 1; i < 2*s; i++) {
            sum[i] = sum[i-1] + elements[i%s];
        }
        
        for(int i = 0; i < s; i++) {
            set.add(sum[i]);
            for(int j = 1; j < s; j++) {
                set.add(sum[j+i]-sum[j-1]);
            }
        }
        
        return set.size();
    }
}
