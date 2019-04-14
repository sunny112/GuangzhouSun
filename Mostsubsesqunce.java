package amazonOA;

import java.util.ArrayList;
import java.util.List;

/**********
 * 有一串字符a-z，可能会重复。尽量把它分解成最多的subsequence，使得一个字符不会同时出现多个subsequence里。返回每个subsequence里的字符个数
 * 比如说，
 * input: ['a', 'b', 'a', 'c', 'd'].
 * output: [3, 1, 1]
 *
 * input: ['a', 'b', 'a', 'b', 'd']
 * output: [4, 1]
 */
public class Mostsubsesqunce {
    public List<Integer> getLeastSubsequence(Character[] input){
        List<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0) return res;
        int[] map = new int[26];
        for (int i = 0; i < input.length; i++) {
            map[input[i] - 'a'] = i;
        }
        int last = 0;
        int start = 0;
        for (int i = 0; i < input.length; i++) {
            last = Math.max(last, map[input[i] - 'a']);
            if (last == i) {
                res.add(last - start + 1);
                start = last + 1;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Character[] input1 = {'a', 'b', 'a', 'c', 'd'};
        Character[] input2 = {'a', 'b', 'a', 'b', 'd'};
        Mostsubsesqunce sol = new Mostsubsesqunce();
        System.out.println(sol.getLeastSubsequence(input1));
        System.out.println(sol.getLeastSubsequence(input2));
    }
}
