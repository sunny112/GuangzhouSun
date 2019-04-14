package amazonOA;

import java.util.*;

class listComparator implements Comparator<List<Integer>>{
    public int compare(List<Integer> o1, List<Integer> o2) {
        int diff = o1.get(1) - o2.get(1);
        return diff;
    }
}
public class Flight {
    /****************

    Two-sum变种
    a. 背景是一架飞机最多飞8000km， 要去两个地方。地点一从list A里面选，地点二从list B里面选。list = [[地点名称，距离]]。 距离简单的考虑成不论从哪里到这个地方都是这个距离就行。求最多能飞的路线的集合
    例：maxdistance = 8000, A = [[1, 3000],[2,4000]], B = [[3, 4000],[4,2000],[5, 5000]]  return [[1, 5], [2, 4]]
    */
    //time: O(n^2)

//写法1：brute froce
    public static List<List<Integer>> getRouteI(List<List<Integer>> foregroundAppList, List<List<Integer>> backgroundAppList, int deviceCapacity){
        List<List<Integer>> res = new ArrayList<>();
        if(foregroundAppList == null || backgroundAppList == null || foregroundAppList.size() == 0 || backgroundAppList.size() == 0){
            return res;
        }
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        int max = 0;
        for(int i = 0; i < foregroundAppList.size(); i++){
            for(int j = 0; j < backgroundAppList.size(); j++){
                int sum = foregroundAppList.get(i).get(1) + backgroundAppList.get(j).get(1);
                if(sum < max && sum < deviceCapacity){
                    max = sum;
                }
                if(!map.containsKey(sum)){
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(Arrays.asList(foregroundAppList.get(i).get(0), backgroundAppList.get(j).get(0)));
            }}
            return map.containsKey(deviceCapacity) ? map.get(deviceCapacity) : map.get(max);
    }

    /***********
     two sum closest变形，背景是无人机送货，无人机有最大里程，然后给了两个list，
     分别是出发和返回的里程数，数据类型是List<List<Integer>>，list里面只有id和里程两个值，要求找出所有出发和返回里程数之和最接近无人机最大里程的pair。
     比如，最大里程M = 11000，forwarding = [[1, 1000],[2, 7000],[3, 12000]], retrun = [[1, 10000],[2, 9000],[3, 3000],[4, 2000]], 最接近的里程和是10000，
     所以结果是[[1, 2],[2, 3]].思路：先用两个list sort一下，因为题目里没说给的list是sort好的，然后用two pointers找到最接近的里程，
     接着把return list里的id和里程的关系放到hashmap里，用two sum的解法就弄出来了。这题test cases全过了。
     */
//method2:two pointer
//time:Mlogm + nlogn?? space:n
public List<List<Integer>> getRouteII(List<List<Integer>> forwarding, List<List<Integer>> returning, int M){
    List<List<Integer>> res = new ArrayList<>();
    if (forwarding == null || returning == null || forwarding.size() == 0 || returning.size() == 0 || M == 0) return res;
//    Collections.sort(forwarding, new listComparator());
//    Collections.sort(returning, new listComparator());
    Collections.sort(forwarding, (a, b) -> (a.get(1) - b.get(1)));
    Collections.sort(returning, (a, b) -> (a.get(1) - b.get(1)));
    int i = 0;
    int j = returning.size() - 1;
    int max = 0;
    while (i < forwarding.size() && j >= 0) {
        int temp = forwarding.get(i).get(1) + returning.get(j).get(1);
        if (temp <= M){
            max = Math.max(max, temp);
            i++;
        }else{
            j--;
        }
    }
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (List<Integer> list : returning) {
        if (!map.containsKey(list.get(1))){
            List<Integer> sublist = new ArrayList<>();
            sublist.add(list.get(0));
            map.put(list.get(1), sublist);
        }else{
            List<Integer> slist = map.get(list.get(1));
            slist.add(list.get(0));
            map.put(list.get(1), slist);
        }
    }
    for (List<Integer> forward: forwarding) {
        int remain = max - forward.get(1);
        if (map.containsKey(remain)){

            for (Integer num: map.get(remain)){
//                List<Integer> sub = new ArrayList<>();
//                sub.add(forward.get(0));
//                sub.add(num);
                res.add(Arrays.asList(forward.get(0), num));
                //res.add(sub);
            }

        }
    }
    return res;
}
    public static void main(String[] args){
        Flight sol = new Flight();
        List<Integer> e1 = new ArrayList<>();
        e1.add(4);
        e1.add(1000);
        List<Integer> e2 = new ArrayList<>();
        e2.add(2);
        e2.add(3000);
        List<Integer> e3 = new ArrayList<>();
        e3.add(3);
        e3.add(4000);
        List<Integer> e4 = new ArrayList<>();
        e4.add(5);
        e4.add(4000);
        List<Integer> e5 = new ArrayList<>();
        e5.add(1);
        e5.add(5000);
        List<Integer> e6 = new ArrayList<>();
        e6.add(1);
        e6.add(2000);
        List<Integer> e7 = new ArrayList<>();
        e7.add(3);
        e7.add(5000);
        List<Integer> e8 = new ArrayList<>();
        e8.add(2);
        e8.add(5000);
        List<Integer> e9 = new ArrayList<>();
        e9.add(4);
        e9.add(6000);
        List<List<Integer>> forwardlist = new ArrayList<>();
        forwardlist.add(e1);
        forwardlist.add(e2);
        forwardlist.add(e3);
        forwardlist.add(e4);
        forwardlist.add(e5);
        List<List<Integer>> returnlist = new ArrayList<>();
        returnlist.add(e6);
        returnlist.add(e7);
        returnlist.add(e8);
        returnlist.add(e9);
        System.out.println(sol.getRouteII(forwardlist, returnlist, 9000));
        //[[2, 4], [3, 3], [3, 2], [5, 3], [5, 2]]
    }

    /*
    Memory Consumption：
    有两个数组 如下形式, 每个代表一个application, 第一个数代表index, 代表application会使用的内存
    [[1, 100], [2, 200], [3, 500] ...]
    [[1, 200], [2, 300], [3, 900] ...]
    给一个最大的Memory数例如1000.
    从第一和第二个数组中个找出一个application 总内存小于1000
    这个题的答案是[3, 2], 既取第一个array中的3, 500和第二个array中的2, 300 总共用800 memory < 1000
    去重
     */
    //有序：O(n), 无序：O(nlogn)
//    public static List<List<Integer>> memory(List<List<Integer>> A, List<List<Integer>> B, int target){
//        List<List<Integer>> res = new ArrayList<>();
//        if (A == null || B == null || A.size() == 0 || B.size() == 0 ){
//            return res;
//        }
//    }

    /*************************

    Two-sum变种:
    1. 飞机上放电影，给一个int[] dur代表所有电影的时间长度, 和一个int 飞行时间长度，要选取2部电影，保证2部电影加起来的时间小于等于飞行时长减去30分钟。
    然后选取2部电影，使得总长度最长，如果有多组总长度一样长的，选取包含单独最长的电影的组合。

    */
    /*******method1: two pointers*/
    //time:O(nlogn), space:O(n)
    //1. Create a class Pair to store movie duration and index
    //2. Sort pairs based on movie duration
    //3. Two sum on the sorted pairs
    public static int[] getMovieIndices(int[] movieDurations, int flightDuration){
        Pair[] pairs = createPairs(movieDurations);
        //sort the pair array based on movie duration;
       // Arrays.sort(pairs, Comparator.comparing((pair1, pair2) -> pair1.movieDuration - pair2.movieDuration));
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.movieDuration - o2.movieDuration == 0) return 0;
                return o1.movieDuration - o2.movieDuration < 0 ? -1 : 1;
            }
        });
        int largestDurationSum = 0;
        int[] res = new int[]{-1, -1};
        int i = 0;
        int j = pairs.length - 1;
        while(i < j){
            int durationSum = pairs[i].movieDuration + pairs[j].movieDuration;
            if(durationSum > flightDuration - 30){
                j--;
            }else if(durationSum < flightDuration - 30){
                //when durationSum == largestDurationSum, we already found a combination where
                //one has the longest duration
                if(durationSum > largestDurationSum){
                    res = new int[]{pairs[i].index, pairs[j].index};
                    largestDurationSum = durationSum;
                }
                i++;
            } else {
                return new int[]{pairs[i].index, pairs[j].index};
            }
        }
        return res;
    }
    private static Pair[] createPairs(int[] movieDurations){
        Pair[] pairs = new Pair[movieDurations.length];
        for(int i = 0; i < movieDurations.length; i++){
            pairs[i] = new Pair(i, movieDurations[i]);
        }
        return pairs;
    }
    //a pair consists of the duration of a movie and its index in the duration array
    static class Pair{
        int index;
        int movieDuration;

        Pair(int index, int movieDuration){
            this.index = index;
            this.movieDuration = movieDuration;
        }
    }

    /******* method2: binary search*/
    //time: nlogn space:1
    public int[] aerial_Movie(int t, int[] dur) {
        if (dur == null || dur.length == 0 || t <= 0) return new int[0];
        if (dur.length < 2) return dur;
        t -= 30;
        int[] res = new int[2];
        int tempMax = 0;
        Arrays.sort(dur);
        for (int i = dur.length - 1; i >= 1; i--) {
            if (dur[i] < t){
                int ans = binarySearch(dur, t - dur[i], i - 1);
                if (ans + dur[i] > tempMax && ans <= t - dur[i]) {
                    tempMax = ans + dur[i];
                    res[0] = ans;
                    res[1] = dur[i];
                }
            }

        }
        return res;
    }
    private int binarySearch(int[] dur, int t, int end) {
        int left = 0;
        int right = end;
        while (left <= right) {
            int mid = right + (left - right) / 2;
            if (t < dur[mid]) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        if (right == -1) return dur[0];
        return dur[right];
    }

}
