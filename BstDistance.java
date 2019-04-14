package amazonOA;

import java.util.HashSet;
import java.util.Set;
class TreeNode1 {
    TreeNode1 left;
    TreeNode1 right;
    int val;
    TreeNode1(int val){
        this.val = val;
    }
}
public class BstDistance {

    public int bstDistance(int[] numbers, int node1, int node2) {
        if (numbers == null || numbers.length == 0) return -1;
        if (!checkNode(numbers, node1, node2)){
            return -1;
        }
        TreeNode1 root = constructTree(numbers);
        while (node1 < root.val && node2 < root.val || node1 > root.val && node2 > root.val){
            if (node1 < root.val && node2 < root.val) {
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return getDistance(root, node1) + getDistance(root, node2);
    }
    private TreeNode1 constructTree(int[] numbers){
        TreeNode1 root = new TreeNode1(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            bst(root, numbers[i]);
        }
        return root;
    }
    private TreeNode1 bst(TreeNode1 root, int node) {
        if (root == null) return new TreeNode1(node);
        if (root.val < node) {
            root.right = bst(root.right, node);
        }else{
            root.left = bst(root.left, node);
        }
        return root;
    }
    private boolean checkNode(int[] numbers, int node1, int node2) {
        Set<Integer> set = new HashSet<>();
        for (int num: numbers) {
            set.add(num);
        }
        if (set.contains(node1) && set.contains(node2)) {
            return true;
        }
        return false;
    }
    private int getDistance(TreeNode1 root, int node) {
        int count = 0;
        TreeNode1 cur = root;
        while (cur.val != node) {
            count++;
            if (cur.val < node) {
                cur = cur.right;
            }else if(cur.val > node) {
                cur = cur.left;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        BstDistance bst = new BstDistance();
        int[] arr = new int[]{5,6,3,1,2,4};
        System.out.println(bst.bstDistance(arr, 2, 4));

    }
}
