package amazonOA;

/********
 * 大意是说Amazon 开了新的warehouse，要用卡车给其他地方送货
 * 参数：
 * int N, 代表总共有N 个地点
 * List<List<Integer>> 地点的坐标
 * int M,代表需要送的crate数量
 *
 * output：一个List<List<Integer>> 代表送货的地点坐标x,y
 * 其实就是让你计算距离卡车最近的M个地点.
 * 需要注意点是题目里面没有给卡车的位置，根据给的例子猜出是原点（0，0）
 * 例1：N = 3, M = 2, List<List<Ingeter>> 是 [[2,3][3,4],[1,-3]].
 * output: [[2,3],[1,-3]]
 *
 * 例2： N=3， M=6， List<List<Integer>> 是[[1,8],[2,4],[8,9],[5,3],[2,7],[3,5]]
 * output: [[2,4],[5,3],[3,5]]
 */

import java.util.*;

/*****
 * 卡车装M个箱子， N个地点List<Integer> M<N 列出最近的M个位置。 要注意输入不正常的情况，比如只有一个输入
 */
public class Warehouse {
    class Point{
        List<Integer> list;
        int distance;
        Point(List<Integer> list, int distance) {
            this.list = list;
            this.distance = distance;
        }
    }
    public List<List<Integer>> ClosestXdestinations(int numDestinations, List<List<Integer>> allLocations, int numDeliveries) {
        if (allLocations == null || allLocations.size() == 0 || allLocations.size() < numDeliveries) {
            return new ArrayList<>();
        }
        PriorityQueue<Point> minHeap = new PriorityQueue<>(numDeliveries, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int diff = o1.distance - o2.distance;
//                if (diff == 0) {
//                    diff = o2.list.get(0) - o1.list.get(0);
//                }
//                if (diff == 0) {
//                    diff = o2.list.get(1) - o1.list.get(1);
//                }
                return diff;
            }
        });
        for (List<Integer> location: allLocations) {
            int distance = location.get(0) * location.get(0) + location.get(1) * location.get(1);
            Point point = new Point(location, distance);
            minHeap.offer(point);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numDeliveries && i < numDestinations; i++) {
            res.add(minHeap.poll().list);
        }
        Collections.sort(res, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int diff = o1.get(0) - o2.get(0);
                if (diff == 0) {
                    diff = o1.get(1) - o2.get(1);
                }
                return diff;
            }
        });
        return res;
    }
    public static void main(String[] args) {
        Warehouse wh = new Warehouse();
        List<List<Integer>> all = new ArrayList<>();
        List<Integer> e1 = new ArrayList<>();
        e1.add(1);
        e1.add(8);
        List<Integer> e2 = new ArrayList<>();
        e2.add(2);
        e2.add(4);
        List<Integer> e3 = new ArrayList<>();
        e3.add(8);
        e3.add(9);
        List<Integer> e4 = new ArrayList<>();
        e4.add(5);
        e4.add(3);
        List<Integer> e5 = new ArrayList<>();
        e5.add(2);
        e5.add(7);
        List<Integer> e6 = new ArrayList<>();
        e6.add(3);
        e6.add(6);
        all.add(e1);
        all.add(e2);
        all.add(e3);
        all.add(e4);
        all.add(e5);
        all.add(e6);
        System.out.println(wh.ClosestXdestinations(6, all, 3));
    }
}
