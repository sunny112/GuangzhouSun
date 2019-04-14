package amazonOA;

import java.lang.reflect.Array;
import java.util.*;

/***********
 * 城市建路题。题目意思是有一定数量的城市，城市之间已经有了一些道路。还有一些可以供选择的道路来建设。每个新建的道路有 cost。问如果要连接所有的城市，新建路的最小的 cost 是多少。举个栗子：
 * Input 如下：
 * numTotalAvailableCities = 6
 * numTotalAvailableRoads = 3
 * roadsAvailable = [[1, 4], [4, 5], [2, 3]].
 * numNewRoadsConstruct = 4
 * costNewRoadsConstruct = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
 * Output 应该是： 7
 * 解释：numTotalAvailableCities = 6 意思是城市的编号从 1 到 6。基于提供的 roadsAvailable list, 这 6 个城市中 已经形成了三个岛， 分别为 [1, 4, 6], [2, 3] 和 [6]。
 * 现在要从 costNewRoadsConstruct list 中选一些路来建使得所有的城市都被连接。这个例子中，显然要选择[1, 2, 5] 和 [1, 6, 2] 这两条路。总的 cost 就是 5 + 2 = 7。
 *
 * 解题思路：
 * 这是个最小生成树（MST）问题。但要注意整个图中已经有一些边了，不是从0开始的最小生成树。具体来说，可以先Union-Find所有已经有的路 in roadsAvailable list，
 * 然后把所有可以建的路 in costNewRoadsConstruct list 按照 cost 排序放入 min-heap。然后每次从 min-heap 中拿出最小 cost 的路来接着 Union-Find整个图。每次需要Union的时候，累积目前为止的 cost。
 * 当总的 edges 数目等于总的 vertices 数目减 1 时，整个图就被构建成了一颗树。这时输入累积的cost作为输出。
 * 注意：
 * 这个题不太容易过所有的 test case （目前有19个test cases），因为有些坑需要避免。
 * 1. 城市的ID是从1开始，不是从0开始。所以UnionFind的时候要多注意。.
 * 2. 输入的roadsAvailable list 和 costNewRoadsConstruct list 互相之间可能有重复。所以不要在算Graph中的 edges 数目的时候要格外注意。
 * //
 */
public class BuildRoad {
    public int getMinimumCostToConstruct(int numTotalAvailableCities, int numTotalAvailableRoads, List<List<Integer>> roadsAvailable, int numNewRoadsConstruct,
                                         List<List<Integer>> costNewRoadsConstruct){
            if (numTotalAvailableCities < 2 || numTotalAvailableRoads > numTotalAvailableCities - 1) return 0;
            UnionFind uf = new UnionFind(numTotalAvailableCities);
//            int existCount = 0;
//            for (List<Integer> road: roadsAvailable){
//                int city1 = road.get(0);
//                int city2 = road.get(1);
//                if (!uf.find(city1, city2)){
//                    uf.union(city1, city2);
//                    existCount++;
//                }
//            }
//        PriorityQueue<Connection> minheap = new PriorityQueue<>(numNewRoadsConstruct, (a, b) -> (
//                a.cost - b.cost
//        ));
//            for (List<Integer> newRoad: costNewRoadsConstruct) {
//                Connection newConnect = new Connection(newRoad.get(0), newRoad.get(1), newRoad.get(2));
//                minheap.offer(newConnect);
//            }
//            List<Connection> res = new ArrayList<>();
//            while (!minheap.isEmpty()) {
//                Connection cur = minheap.poll();
//                if (!uf.find(cur.city1, cur.city2)) {
//                    uf.union(cur.city1, cur.city2);
//                    res.add(cur);
//                }
//            }
//            if (res.size() < numTotalAvailableCities - 1) {
//                return -1;
//            }
//            int sum = 0;
//            for (Connection temp: res) {
//                sum += temp.cost;
//            }
        int re
        return sum;

    }
    class Connection{
        int city1;
        int city2;
        int cost;
        Connection(int city1, int city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }
    class UnionFind{
        int[] ids;
        UnionFind(int size) {
            this.ids = new int[size + 1];
            for (int i = 0; i < size + 1; i++) {
                ids[i] = i;
            }
        }
        public int root (int i) {
            while (ids[i] != i) {
                i = ids[i];
            }
            return i;
        }
        public boolean find(int i, int j){
            return root(i) == root(j);
        }
        public void union(int a, int b){
            int idxa = root(a);
            int idxb = root(b);
            if (idxa != idxb) {
                ids[idxa] = idxb;
            }
        }
    }

    public static void main(String[] args){
        BuildRoad br = new BuildRoad();
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> r1 = new ArrayList<>();
        r1.add(1);
        r1.add(2);
        List<Integer> r2 = new ArrayList<>();
        r2.add(2);
        r2.add(3);
        List<Integer> r3 = new ArrayList<>();
        r3.add(4);
        r3.add(5);
        List<Integer> r4 = new ArrayList<>();
        r4.add(3);
        r4.add(5);
        List<Integer> r5 = new ArrayList<>();
        r5.add(1);
        r5.add(6);
        List<Integer> r6 = new ArrayList<>();
        r6.add(2);
        r6.add(4);
        r.add(r1);
        r.add(r2);
        r.add(r3);
        r.add(r4);
        r.add(r5);
        r.add(r6);

        List<List<Integer>> lr = new ArrayList<>();
        List<Integer> lr1 = new ArrayList<>();
        lr1.add(1);
        lr1.add(6);
        lr1.add(410);
        List<Integer> lr2 = new ArrayList<>();
        lr2.add(2);
        lr2.add(4);
        lr2.add(800);
        List<Integer> lr3 = new ArrayList<>();
        lr3.add(2);
        lr3.add(3);
        lr3.add(2);
        List<Integer> lr4 = new ArrayList<>();
        lr4.add(1);
        lr4.add(4);
        lr4.add(10);
        lr.add(lr1);
        lr.add(lr2);
//        lr.add(lr3);
//        lr.add(lr4);
        System.out.println(br.getMinimumCostToConstruct(6, 6, r, 2, lr));
    }
}
