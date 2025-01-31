class Solution {
    public long[] solution(int n, long left, long right) {
        
        Long len = right-left+1;
        long[] answer = new long[len.intValue()];
        
        for(int i = 0; i<answer.length; i++) {
            long index = i+left+1;
            
            long h = (index%n!=0)?(index/n + 1):index/n;
            long y = (index%n!=0)?index%n:(index%n+n);
            
            answer[i] = (y<=h)?h:y;
        }
        return answer;
    }
}