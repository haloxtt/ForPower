/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 */
public class LongestCommonPrefix {
    public static String lon(String[] strs) {
        if (strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1;j < strs.length; j++) {
                if ((strs[j].length() - 1 < i) || (strs[j].charAt(i) != ch)) {
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        String[] arr = {"fl","fll"};
        System.out.println(lon(arr));
    }
}
