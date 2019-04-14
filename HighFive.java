package amazonOA;

import java.util.*;

class PageTime{
    int pageId;
    int renderTime;
    PageTime(int pageId, int renderTime){
        this.pageId = pageId;
        this.renderTime = renderTime;
    }
}
public class HighFive {
    public Map<Integer, Double> aveFive(int renderCount, List<PageTime> renderTimeOfPages) {
        Map<Integer, Double> res = new HashMap<>();
        Map<Integer, PriorityQueue<PageTime>> map = new HashMap<>();
        int k = 5;
        for (PageTime ele: renderTimeOfPages) {
            if (!map.containsKey(ele.pageId)) {
                PriorityQueue<PageTime> minHeap = new PriorityQueue<>(k, (a, b) -> a.renderTime - b.renderTime);
                minHeap.offer(ele);
                map.put(ele.pageId, minHeap);
            }
            map.get(ele.pageId).offer(ele);
            if (map.get(ele.pageId).size() > k) {
                map.get(ele.pageId).poll();
            }
        }
        for (Map.Entry<Integer, PriorityQueue<PageTime>> entry: map.entrySet()){
            int pageId = entry.getKey();
            PriorityQueue<PageTime> pq = entry.getValue();
            double ave = 0;
            int num = pq.size();
            while (!pq.isEmpty()) {
                ave += pq.poll().renderTime;
            }
            ave /= num;
            res.put(pageId, ave);
        }
        return res;
    }
    public static void main(String[] args) {
        PageTime p1 = new PageTime(1,40);
        PageTime p2 = new PageTime(1,90);
        PageTime p3 = new PageTime(2,0);
        PageTime p4 = new PageTime(1,40);
        PageTime p5 = new PageTime(3,40);
        List<PageTime> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        HighFive sol = new HighFive();
        System.out.println(sol.aveFive(5, list));
    }
}
