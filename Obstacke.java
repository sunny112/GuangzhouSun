package amazonOA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Obstacke {
    //time:o(n); space:o(n)
    private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    public int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot){
        if (lot == null || lot.size() == 0 || lot.get(0).size() == 0){
            return 0;
        }
        if(numRows == 0 && numColumns == 0){
            return 0;
        }
        boolean[][] visited = new boolean[numRows][numColumns];
        Queue<int[]> queue = new LinkedList<>();
        int step = 1;
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int adjX = cur[0] + dx[j];
                    int adjY = cur[1] + dy[j];
                    if ((adjX >= 0) && (adjX < numRows) && (adjY >= 0) && (adjY < numColumns) && !visited[adjX][adjY]
                            && lot.get(adjX).get(adjY) != 0) {
                        if(lot.get(adjX).get(adjY) == 9 || (adjX == numRows && adjY == numColumns)){
                            return step;
                        }
                        visited[adjX][adjY] = true;
                        queue.offer(new int[]{adjX, adjY});
                    }
                }
            }
            step++;
        }
        return -1;
    }
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);l1.add(0);l1.add(0);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);l2.add(0);l2.add(0);
        List<Integer> l3 = new ArrayList<>();
        l3.add(1);l3.add(9);l3.add(0);
        List<List<Integer>> all = new ArrayList<>();
        all.add(l1);
        all.add(l2);
        all.add(l3);
        Obstacke sol = new Obstacke();
        System.out.println(sol.removeObstacle(3,3,all));
    }
}
