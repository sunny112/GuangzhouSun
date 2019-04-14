package amazonOA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAnagram {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        Map<Character, Integer> map = new HashMap<>();

        for(char x: p.toCharArray())
            map.put(x, map.getOrDefault(x, 0) + 1);

        int start = 0;
        int count = 0;

        Map<Character, Integer> curr = new HashMap<>();

        for(int i = 0;i < s.length();i++) {
            char x = s.charAt(i);

            if(map.containsKey(x)) {
                curr.put(x, curr.getOrDefault(x, 0) + 1);
                count++;

                while(curr.get(x) > map.get(x)) {
                    char left = s.charAt(start);
                    curr.put(left, curr.get(left) - 1);
                    start++;
                    count--;
                }

                if(count == p.length()) {
                    result.add(start);
                    char left = s.charAt(start);
                    curr.put(left, curr.get(left) - 1);
                    start++;
                    count--;
                }
            }
            else {
                curr.clear();
                start = i + 1;
                count = 0;
            }
        }

        return result;
    }

}
