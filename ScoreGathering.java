package amazonOA;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class TreeNode2{
    int val;
    TreeNode2 left;
    TreeNode2 right;
    int freq;
    TreeNode2(int val, int freq) {
        this.val = val;
        this.freq = freq;
    }
}
    public class ScoreGathering {
        public String scoreGather(List<Integer> stream){
            if(stream==null || stream.size()<1) return "";

            TreeNode2 root = null;
            for(Integer i: stream){
                root = insert(root, (int)i);
            }

            return serializeTree(root);



        }

        private TreeNode2 insert(TreeNode2 root, int target){
            if(root==null) return new TreeNode2(target, 1);
            if(target > root.val){
                root.right = insert(root.right,target);
            }else if(target < root.val){
                root.left = insert(root.left, target);
            }else{
                root.freq =+1;
            }
            return root;

        }

        private String serializeTree(TreeNode2 root){

            List<StringBuilder> res = new ArrayList<>();
            Queue<TreeNode2> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                int size = q.size();
                StringBuilder level = new StringBuilder();
                for(int i =0; i<size; i++){
                    TreeNode2 cur = q.poll();
                    if(cur==null){
                        level.append(",");
                    }else{
                        level.append(cur.val+":"+cur.freq+",");
                        q.offer(cur.left);
                        q.offer(cur.right);
                    }
                }
                res.add(level);
            }

            res.remove(res.size()-1);
            //delete last ,
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i<res.size(); i++){
                if(i==res.size()-1){//last level need to delete last ,
                    StringBuilder sb = res.get(i);
                    sb.deleteCharAt(sb.length()-1);
                }
                ans.append(res.get(i));
            }
            return ans.toString();

        }

        private static void debug(String s){
            System.out.println(s);
        }

        public static void main(String[] args) {
            ScoreGathering sg = new ScoreGathering();
            String res1 = sg.scoreGather(Arrays.asList(4,2,5,5,6,1,4));
            String res2 = sg.scoreGather(Arrays.asList(4,1, 2,3, 4, 5, 6, 7));
            String res3 = sg.scoreGather(Arrays.asList(0));
            String res4 = sg.scoreGather(new ArrayList<Integer>());
            debug(res1);
            debug(res2);
            debug(res3);
            debug(res4);
        }
    }


