package amazonOA;

import java.util.*;

public class FriendCycle {
    static class UnionFind{
        Map<String, String> father = new HashMap<String, String>();
        public UnionFind(String[][] arr) {
            for(int i = 0; i < arr.length; i++){
                String m = arr[i][0];
                father.put(m, m);
                String n = arr[i][1];
                father.put(n, n);
            }// make each node points to itself first, for making the end node points to itself at least.
        }

        public String compressed_find(String x){
            String parent = father.get(x);
            while(!parent.equals(father.get(parent))){
                parent = father.get(parent);
            }

            String fa = x;
            while(parent != father.get(fa)){
                String temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }

        public void union(String a, String b){
            String aFather = compressed_find(a);
            String bFather = compressed_find(b);
            if(aFather.compareTo(bFather) < 0)
                father.put(aFather, bFather);//make sure the all the nodes on the same network point to the shared root.
            else if (aFather.compareTo(bFather) > 0)
                father.put(bFather, aFather);
        }
    }
    public static String[] findLargestFriendCircle(String[][] arr) {

        UnionFind uf = new UnionFind(arr);

        for(int i = 0; i < arr.length; i++)
            uf.union(arr[i][0], arr[i][1]);

        Map<String, List<String>> map = new HashMap<>();
        for(String key : uf.father.keySet()){
            String root = uf.compressed_find(key);
            if(map.containsKey(root)){
                map.get(root).add(key);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(key);
                map.put(root, list);
            }
        }
        int max = 0;
        String maxKey = "";
        for(String str : map.keySet()){
            if(map.get(str).size() > max || map.get(str).size() == max && str.compareTo(maxKey) < 0){
                max = map.get(str).size();
                maxKey = str;
            }
        }
        List<String> list = map.get(maxKey);
        String[] result = new String[list.size()];
        for(int i = 0; i < list.size(); i ++){
            result[i] = list.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        String[][] input = new String[][] {{"itemA", "itemB"},{"itemB", "itemC"},{"itemD", "itemE"}};
        String[] res = findLargestFriendCircle(input);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
        System.out.println(Arrays.toString(res));
    }
}
