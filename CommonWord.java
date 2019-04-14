package amazonOA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CommonWord {
        public String mostCommonWord(String paragraph, String[] banned) {

            Map<String,Integer> map = new HashMap<>();
            Set<String> set = new HashSet<>();
            for(String s : banned)
                set.add(s);

            String[] words = paragraph.toLowerCase().split("[!?',;. ]");

            String res = null;
            int maxTimes = 0;

            for(String s : words){
                if(s.length()==0 || set.contains(s))
                    continue;

                if(map.get(s) == null)
                    map.put(s,1);
                else
                    map.put(s,map.get(s) + 1);

                if(map.get(s) > maxTimes){
                    res = s;
                    maxTimes = map.get(s);
                }
            }

            return res;
        }

}
