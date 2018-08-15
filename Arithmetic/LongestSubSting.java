import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。

 示例：

 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class LongestSubSting {
    public static void main(String[] args) {
        String a = "aavdsufgjha";
        System.out.println(lengthOfLongestSubstring(a));
    }
    public static int lengthOfLongestSubstring(String s) {
        if (s.equals(""))
            return 0;
        int prev = 0;//上一个出现重复字符的下标的下一位
        int maxlength = 0;//最大字符长度
        int sub = 0;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();//构建一个HashMap，K为字符的值，V为字符出现的下标
        map.put(s.charAt(0),0);//将字符串的第一位放到map里
        maxlength++;
        for (int i = 1; i < s.length(); i++) {
            Character index = s.charAt(i);
            if (map.containsKey(index) && map.get(index) >= prev) {
                prev = map.get(index)+1;//如果出现了重复字符串，并且出现在prev引用的后面，prev后移
            }
            sub = i-prev+1;
            maxlength = Math.max(sub , maxlength);//记录当前最大长度
            map.put(s.charAt(i),i);//将新的字符放入，如果之前出现过，则是新的下标
        }
        return maxlength;
    }

}
