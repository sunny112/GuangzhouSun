package amazonOA;

import java.util.ArrayList;
import java.util.List;

/********
 * coding一道是two sum的变种，找不超过target的最大sum，另一道是subtree with maximum average，题目说了叶子结点不能算subtree，当时没注意到，在这里卡了一会。
 */
class CategoryNode{
    int value;
    ArrayList<CategoryNode> subCategoryNode;
    public CategoryNode(){
        subCategoryNode = new ArrayList<>();
    }
    public  CategoryNode(int tenure){
        this.value = tenure;
        this.subCategoryNode = new ArrayList<>();
    }
}
public class SubtreeMaxAverage {

    class ResultType{
        CategoryNode node;
        public int sum, size;
        public ResultType(CategoryNode node, int sum, int size){
            this.node = node;
            this.sum = sum;
            this.size = size;
        }
    }
    /***********
     * CategoryNode
     */

    public ResultType subRes = null;
    public CategoryNode getMostPopularNode(CategoryNode rootCategory) {
            if (rootCategory == null) return null;
            ResultType rootRes = helperC(rootCategory);
            return subRes.node;
    }
    private ResultType helperC(CategoryNode root){
        if (root.subCategoryNode == null) return new ResultType(null, 0, 0);
        List<ResultType> results = new ArrayList<>();
        for (CategoryNode node: root.subCategoryNode) {
            results.add(helperC(node));
        }
        int childSum = 0;
        int childSize = 0;
        for (ResultType r: results) {
            childSum += r.sum;
            childSize += r.size;
        }
        ResultType curRes = new ResultType(root, childSum + root.value, childSize + 1);
        if (subRes == null || curRes.sum * subRes.size > curRes.size * subRes.sum){
            if (curRes.size > 1) {
                subRes = curRes;
            }
        }
        return curRes;
    }
    public static void main(String[] args){
        CategoryNode node1 = new CategoryNode(20);
        CategoryNode node2 = new CategoryNode(12);
        CategoryNode node3 = new CategoryNode(18);
        CategoryNode node4 = new CategoryNode(11);
        CategoryNode node5 = new CategoryNode(2);
        CategoryNode node6 = new CategoryNode(3);
        CategoryNode node7 = new CategoryNode(15);
        CategoryNode node8 = new CategoryNode(8);
        node1.subCategoryNode.add(node2);
        node1.subCategoryNode.add(node3);
        node2.subCategoryNode.add(node4);
        node2.subCategoryNode.add(node5);
        node2.subCategoryNode.add(node6);
        node3.subCategoryNode.add(node7);
        node3.subCategoryNode.add(node8);
        SubtreeMaxAverage sol = new SubtreeMaxAverage();
        System.out.println(sol.getMostPopularNode(node1).value);

    }
}
