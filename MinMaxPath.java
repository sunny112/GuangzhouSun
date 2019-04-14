package amazonOA;
/*
Maximum Minimum Path
给一个int[][]的matirx，对于一条从左上到右下的path p_i，p_i中的最小值是m_i，求所有m_i中的最大值。只能往下或右
比如：
[8, 4, 7]
[6, 5, 9]
有3条path：
8-4-7-9 min: 4
8-4-5-9 min: 4
8-6-5-9 min: 5
return: 5
 */
public class MinMaxPath {
    public int helper(int[][] matrix){
        int[] result = new int[matrix[0].length];
        result[0] = matrix[0][0];
        for(int i=1; i<matrix[0].length; i++){
            result[i] = Math.min(result[i-1], matrix[0][i]);
        }
        for(int i=1; i<matrix.length; i++){
            result[0] = Math.min(matrix[i][0], result[0]);
            for(int j=1; j<matrix[0].length; j++){
                result[j] = (result[j]<matrix[i][j] && result[j-1]<matrix[i][j])?
                        Math.max(result[j-1], result[j]):matrix[i][j];
            }
        }
        return result[result.length-1];
    }
    public static void main(String[] args){
        int[][] m = {
                {8, 4, 7},
                {6, 5, 9}
        };
        MinMaxPath sol = new MinMaxPath();
        System.out.println(sol.helper(m));
    }
}
