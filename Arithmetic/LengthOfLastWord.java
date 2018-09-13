/**
 * leetcode 58
 *给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

 如果不存在最后一个单词，请返回 0 。

 说明：一个单词是指由字母组成，但不包含任何空格的字符串。

 示例:

 输入: "Hello World"
 输出: 5
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) != 32) {//找到倒数第一个不为空格的字符
                int prev = i;
                while (prev >= 0) {
                    if (prev == 0 && s.charAt(0) != 32) {
                        return ++length;
                    }
                    if (prev == 0 && s.charAt(0) == 32) {
                        return length;
                    }
                    if (s.charAt(prev) == 32) {
                        return length;
                    }
                    if (s.charAt(prev) != 32) {
                        prev--;
                        length++;
                    }
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        String str = "   aa  ";
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        System.out.println(lengthOfLastWord.lengthOfLastWord(str));
    }
}
