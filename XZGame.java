package amazonOA;

import java.util.Stack;

/*******
 * 输入一个字符串，其中包括整数，Z，X，或者+。整数代表此轮得分，X：当前成绩是double前面一个分数，+：当前成绩是前两个的和，Z：移除前一个成绩，求最后的总成绩和 一颗栗子：
 * 输入["5", "-2", "4", "Z","X", 9, "+", "+"]
 * output: 27
 * 5 : sum = 5 (5)
 * -2 : sum = 5 - 2 = 3 (5, -2)
 * 4 : sum = 3 + 4 = 7 (5, -2, 4)
 * Z : sum = 7 - 4 = 3 (5, -2)
 * X : sum = 3 + -2 * 2 = -1 (4被移除了，前一个成绩是-2，-2被double变成-4) (5, -4)
 * 9 : sum = -1 + 9 = 8 (5, -4, 9)
 * + : sum = 8 + 9 - 4 = 13 (前两个成绩是9和-4) (5, -4, 9)
 * + : sum = 13 + 9 + 5 = 27 (前两个成绩是5 和 9)
 * 最后一个‘+’等于倒数第二个‘+’代表的分数加上9，所以是5+9
 */
public class XZGame {
    public int beginGame(String[] input) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("Z") && !stack.isEmpty()) {
                stack.pop();
            } else if (input[i].equals("X") && !stack.isEmpty()) {
                stack.push(stack.peek() * 2);
            } else if (input[i].equals("+") && !stack.isEmpty()) {
                int temp1 = stack.pop();
                if (!stack.isEmpty()) {
                    int temp2 = stack.peek();
                    stack.push(temp1);
                    stack.push(temp1 + temp2);
                } else {
                    stack.push(temp1);
                }
            } else {
                stack.push(Integer.valueOf(input[i]));
            }
        }
            int sum = 0;
            while (!stack.isEmpty())
                sum += stack.pop();

            return sum;


    }
        public static void main(String[] args) {
            String[] testArray = {"5", "-2", "4", "Z", "X", "9", "+", "+"};
            XZGame sol = new XZGame();
            System.out.print("sum is : " + sol.beginGame(testArray));
        }
}
