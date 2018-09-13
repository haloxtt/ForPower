/**
 * LeetCode 28题
 *实现 strStr() 函数。

 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

 示例 1:

 输入: haystack = "hello", needle = "ll" （needle为空字符串返回0）
 输出: 2
 示例 2:

 输入: haystack = "aaaaa", needle = "bba"
 输出: -1
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle))
            return 0;
        if ("".equals(haystack))
            return -1;
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i < haystack.length() && i + needle.length() <= haystack.length(); i++) {
            int length = needle.length();
            int count = 0;
            boolean flag = true;
            while (length > 0) {
                if (haystack.charAt(i + count) != needle.charAt(count)) {
                    flag = false;
                    break;
                }
                length--;
                count++;
            }
            if (flag)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStr str = new StrStr();
        System.out.println(str.strStr("aaba","ba"));
    }
}
