package amazonOA;

import java.util.*;

/*******
 * 第二题是新题 高尔夫球场，地里也有同学提到了，不过要注意的是，起始点可以从field的四个角选取，所以要选取不同起始点下步数最小的返回
 * 是需要从最小的树开始砍，按照树的高度从低到高的砍，如果不能按照顺序砍就得return -1，把所有的步长加起来算出总结果返回，field里面有0不能走。
 * 把树按照高度sort一下。然后把field当maze，高度低的树为起始点，比它高的为终止点，当成maze题来解，几下布数，全加起来就成。
 * 把任意一个角当做其实点，用个循环把树从低到高的设置成起始和终止点就行啦。
 * 当然，我是把四个角都当做启示点算了一遍然后返回最小的那个。
 * 如果中间发现无法先到达最低高度的树，那就得返回-1.
 * golf球场修场地。
 *
 第二题的思路是不是如下：1. 先找到所有高度的树排序； 2. 用BFS找到四个顶角可以到的最矮树的最短距离；
 3. 然后以找到的这个最矮树为起点，用BFS挨个找依次增加的树，找到就记录，找不到就返回错误； 2，3步骤用的bfs是同样的算法？

 * public int flatFields (int numRows, int numColumns, List<List<Integer>> fields) {}
 * 让小明帮公司球场修场地，给一个二维的链表fields，场地里有坑，不能走。 场地里有树要砍掉。最后目的返回是修好一层的场地的最小步数。
 * Ex1:
 * [
 * [1, 3, 0, 2]
 * [1, 1, 3, 1]
 * ]
 * 上图中的1代表平地，可以走。 0代表坑，不能走。 大于1的数字代表树木，需要砍掉。规则是从上下左右四个角开始任选一个开始走，先砍数字小的树木。 比如2 < 3，那么就得先走2。
 * 上图如果从右下角开始走依次经过的坐标是： （1， 3） -> (0, 3) -> (1, 3) -> (1, 2) -> (1, 1) -> (1, 0) 所以返回的最小步数是5，
 * 因为通过这个路径可以修平第二层的球场[1, 1, 3, 1]， 并且走到左下角终点。
 * Ex2:
 * [
 * [1, 0]
 * [3, 2]
 * ]
 * 上图中的最小步数返回-1因为，没有办法修好一层， 因为从左上角1开始走，不能走到0， 也不能走3， 因为在全局中3比2大，必须先走2。所以就没法走了。
 *
 *
 * 看到两个版本的砍树题，一个是从0，0开始，按树高顺序砍完所有的树，树高唯一， 和LeetCode 675一样。
 *
 * 还有一个版本和楼主的类似，可以从四个角出发，其他条件各个帖子里说的有些出入，比如是全砍完还是只砍完一层就返回？树高是否唯一？
 *
 * 请问楼主的砍树题是哪个版本？能否明确一下具体条件？多谢!
 *
 */
public class Golffield {
    class Node{
        int x;
        int y;
        int val;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int flatFields(int numRows, int numColumns, List<List<Integer>> fields) {
        if (fields == null || fields.size() == 0) return -1;
       // Node[][] fieldNodes = new Node[numRows][numColumns];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.val - n2.val;
            }
        });
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Node cur = new Node(i, j, fields.get(i).get(j));
                if (cur.val > 1)
                    pq.offer(cur);
            }
        }
        PriorityQueue<Integer> min = new PriorityQueue<>(4, (a, b) -> (a - b));
        min.offer(getSteps(fields, 0, 0, pq, numRows, numColumns));
        min.offer(getSteps(fields, numRows - 1, 0, pq, numRows, numColumns));
        min.offer(getSteps(fields, 0, numColumns - 1, pq, numRows, numColumns));
        min.offer(getSteps(fields, numRows - 1, numColumns - 1, pq, numRows, numColumns));
        return min.poll();
    }
    private int getSteps(List<List<Integer>> fields, int x, int y, PriorityQueue<Node> pq, int numRows, int numColumns){
        Node start = new Node(x, y, fields.get(x).get(y));
        int steps = 0;
        while (!pq.isEmpty()) {
            Node target = pq.poll();
            steps += bfs(start, target, fields, pq, numRows, numColumns);
            start = target;
        }
        return steps;
    }
    private int bfs(Node start, Node target, List<List<Integer>> fields, PriorityQueue<Node> pq, int numRows, int numColumns){
        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, -1, 0, 1};
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Node cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int newX = cur.x + dx[i];
                    int newY = cur.y + dy[i];
                    if (newX >= 0 && newY >= 0 && newX < numRows && newY < numColumns && fields.get(newX).get(newY) > 0) {
                        if (fields.get(newX).get(newY) == 1)
                            queue.add(new Node(newX, newY,fields.get(newX).get(newY)));
                        else if ((fields.get(newX).get(newY) > 1 && fields.get(newX).get(newY) == target.val) ||
                                (newX == 0 && newY == numColumns - 1 ||
                                 newX == numRows - 1 && newY == numColumns - 1||
                                 newX == numRows - 1 && newY == 0||
                                 newX == 0 && newY == 0)   ) {
                            steps += fields.get(newX).get(newY);
                           // fields.get(newX).get(newY) = 1;
                            return steps;
                        }
                    }
                }
            }
            steps++;
        }
        return 0;
    }
    class Coordinate {
        int x;
        int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest.size() == 0 || forest.get(0).size() == 0) return 0;

        int n = forest.size();
        int m = forest.get(0).size();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int step = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest.get(i).get(j) != 0 && forest.get(i).get(j) != 1) {
                    heap.offer(forest.get(i).get(j));
                }
            }
        }

        Coordinate origin = new Coordinate(0, 0);
        // If original point is the first step, poll heap (Because it won't be count as step)
        if (forest.get(0).get(0) == heap.peek()) {
            heap.poll();
        }
        queue.offer(origin);

        while (!heap.isEmpty()) {
            visited = new boolean[n][m];
            int target = heap.peek();
            boolean find = false;
            while (!queue.isEmpty() && !find) {
                int size = queue.size();
                step++;

                for (int i = 0; i < size && !find; i++) {
                    Coordinate coordinate = queue.poll();
                    int x = coordinate.x;
                    int y = coordinate.y;
                    visited[x][y] = true;

                    for (int[] direction : directions) {
                        if (x + direction[0] < n && x + direction[0] >= 0 && y + direction[1] < m && y + direction[1] >= 0 && forest.get(x + direction[0]).get(y + direction[1]) != 0 && !visited[x + direction[0]][y + direction[1]]) {
                            if (target == forest.get(x + direction[0]).get(y + direction[1])) {
                                queue.clear();
                                queue.offer(new Coordinate(x + direction[0], y + direction[1]));
                                heap.poll();
                                find = true;
                                break;
                            }
                            visited[x + direction[0]][y + direction[1]] = true;
                            queue.offer(new Coordinate(x + direction[0], y + direction[1]));
                        }
                    }
                }
            }

            if (queue.isEmpty() && !heap.isEmpty()) {
                return -1;
            }
        }
        return step;
    }

}
