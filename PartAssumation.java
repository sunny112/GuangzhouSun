package amazonOA;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*******
 * 第一题：零件组装题。题目意思就是有一堆零件，每个零件的尺寸和组装需要的时间是一样的。输入各个零件的尺寸的list，
 * 要求输出最短的总的 accumulated 组装时间。这么说估计也很难描述清楚，直接上例子：
 * 比如输入的list是 {8， 4， 6， 12}。
 * 1. 先选 4 和 6组装到一起，形成 size 为 10 的新零件。目前为止耗时为10。零件的 list 变为 {8， 10， 12}
 * 2. 再选 8 和 10 组装到一起，形成 size 为 18 的新零件。目前为止耗时为 10 + 18 = 28。零件的 list 变为 {12， 18}
 * 3. 最后 把 12 和 18 组装到一起，形成 size 为 30 的新零件。目前为止耗时为 10 + 18 + 30 = 58。
 * 最后输出 58 就可以了。
 *
 * 解题思路：把所有零件先放到 min-heap (PriorityQueue for Java)中。然后每次 poll 两个最小的，加起来形成新零件，
 * 然后放回到min-heap中。如此循环直至 min-heap 中只有一个零件为止。在循环过程中记录总的累积时间就行了。
 *
 * 1. 有一串正整数，非排序的。先挑两个数字加起来，把这个和记下来，比如说s1。再在剩下的列表里找一个数，加上这个和，
 * 比如说s2. 这样直到加完所有的数字，得到sn。最后把所有的和都加起来，s1 + s2 + ... + sn。求一种组合，使这个和最小。
 * 比如说：
 * Input: 3, 1, 2
 * Output: 9
 * 解释: 1+2 = 3; 3 + 3 = 6; 3+6 = 9
 * Input: 8, 3, 5, 2, 15
 * Output: 66
 * 解释: 2 + 3 = 5; 5+5 = 10; 10 + 8 = 18; 18 + 15 = 33; 5 + 10 + 18 + 33 = 66
 */
public class PartAssumation {
    public int getTime(List<Integer> time){
        if (time == null || time.size() == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(time.size(), (a,b) -> (a - b));
        for (Integer num : time) {
            minHeap.offer(num);
        }
        int sum = 0;
        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            int temp = a + b;
            sum += temp;
            minHeap.offer(temp);
        }
        return sum;
    }
    public static void main(String[] args){
        PartAssumation sol = new PartAssumation();
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(15);
        System.out.println(sol.getTime(list));
    }
}
