package amazonOA;

import java.util.*;

/******
 *
 * 给一个inputString，给一个int k，返回所有长度为k但是有k-1个不同字符的substring，也就是有一个字符可以重复出现
 *此题有16 个隐藏Testcase, 其中8个的情况应该是重复的Substring, 用Set 保存结果然后再存到 List 里面， 最后通过16个case
 */
public class SubstrKDistinct {
    //method1:evan
    private static List<String> substirngwithkminus1distinct(String str, int k) {
        int[] map = new int[256];
        int distinct = 0;
        Set<String> res = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (map[str.charAt(i)] == 0)
                distinct++;
            map[str.charAt(i)]++;
            if (i >= k) {
                map[str.charAt(i - k)]--;
                if (map[str.charAt(i - k)] == 0)
                    distinct--;
                if (distinct == k - 1)
                    res.add(str.substring(i - k + 1, i + 1));

            }
        }
        for (String substr: res){
            ans.add(substr);
        }
        return ans;
    }

    /************
     * k distinct char（长度不限）
     *
     */
    // with exactly k unique characters
    public int countkDist(String str, int k)
    {
        // Initialize result
        int res = 0;

        int n = str.length();

        // To store count of characters from 'a' to 'z'
        int cnt[] = new int[26];

        // Consider all substrings beginning with
        // str[i]
        for (int i = 0; i < n; i++)
        {
            int dist_count = 0;

            // Initializing count array with 0
            Arrays.fill(cnt, 0);

            // Consider all substrings between str[i..j]
            for (int j=i; j<n; j++)
            {
                // If this is a new character for this
                // substring, increment dist_count.
                if (cnt[str.charAt(j) - 'a'] == 0)
                    dist_count++;

                // Increment count of current character
                cnt[str.charAt(j) - 'a']++;

                // If distinct character count becomes k,
                // then increment result.
                if (dist_count == k)
                    res++;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        SubstrKDistinct s = new SubstrKDistinct();
        //System.out.println(s.kdistinctChar("awaglknagawunagwkwagl", 4));
        System.out.println(s.substirngwithkminus1distinct("democracy", 5));
        System.out.println(s.substirngwithkminus1distinct("wawaglknagagwunagkwkwagl", 4));

    }
}
