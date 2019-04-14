package amazonOA;

import java.util.*;

/***********
 * 给你一个log file，List<String>，每个元素代表log file一行，在每个元素里面，有substring，他们用空格分开，
 * 比如"a1ws efg7 i90s", String里面只有字母和数字，第一个substring是id，依据id后面的substring给log file排序，字母在top。
 * 比如input是
 * "fhie 1df8 sfds"
 * "fdsf 2def sees"
 * "efe2 br9o fjsd"
 * “asd1 awer jik9"
 *
 * output是：
 * “asd1 awer jik9"
 * "efe2 br9o fjsd"
 * "fhie 1df8 sfds"
 * "fdsf 2def sees"
 */
public class LogSort {
    class MyCompartor implements Comparator {
        @Override
        public int compare(Object a1, Object a2) {
            String o1 = (String)a1;
            String o2 = (String)a2;
            int idx1 = o1.indexOf(' ');
            int idx2 = o2.indexOf(' ');
            String head1 = o1.substring(0, idx1);
            String head2 = o2.substring(0, idx2);
            String body1 = o1.substring(idx1);
            String body2 = o2.substring(idx2);
            if(body1.equals(body2)) {
                return head1.compareTo(head2);
            } else {
                return body1.compareTo(body2);
            }
        }
    }

    public String[] logSort(String[] logs) {
        // Write your code here
        List<String> list = new ArrayList<String>();
        String [] ans = new String[logs.length];
        int cnt = logs.length - 1;
        for(int i = logs.length - 1; i >= 0; i--) {
            int index = logs[i].indexOf(' ');
            String body = logs[i].substring(index + 1);
            if(body.charAt(0) >= '0' && body.charAt(0) <= '9') {
                ans[cnt--] = logs[i];
            } else {
                list.add(new String(logs[i]));
            }
        }
        MyCompartor mc = new MyCompartor();
        Collections.sort(list, mc);

        cnt = 0;
        for(String i: list) {
            ans[cnt++] = i;
        }
        return ans;
    }
    public static void main(String[] args){
        String[] s = {"fhie 1df8 sfds", "fdsf 2def sees", "efe2 br9o fjsd", "asd1 awer jik9"};
        LogSort sol = new LogSort();
        System.out.println(Arrays.toString(sol.logSort(s)));
    }
}
