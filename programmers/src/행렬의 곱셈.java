class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int a = arr1.length;
        int b = arr2[0].length;
        int mid = arr1[0].length;
        
        int[][] answer = new int[a][b];
        
        for(int i = 0; i<a; i++) {
            for(int j = 0; j<b; j++) {
                for(int l = 0; l<mid; l++) {
                    answer[i][j] += arr1[i][l] * arr2[l][j];
                }
            }
        }
        
        return answer;
    }
}