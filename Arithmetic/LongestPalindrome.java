
/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s.length() == 0)
            return null;
        if (s.length() == 1)
            return s;
        int length = s.length();//长度
        int start = 0;//开始位置
        int maxLength = 1;//最大长度
        //长度为1和2的时候的初始化
        boolean[][] arr = new boolean[s.length()][s.length()];//arr[i][j]表示位置i到j是否为回文串
        for (int i = 0; i < length; i++) {
            arr[i][i] = true;
            if ((i < length - 1) && (s.charAt(i) == s.charAt(i + 1))) {
                arr[i][i + 1] = true;//i和i+1相等时
                start = i;
                maxLength = 2;//当最大长度就是2的时候，返回最后一个相等位置的子串
            }
        }
        for (int i = 3; i <= length; i++) {
            for (int j = 0; j <= length - i; j++) {
                int k = j + i - 1;//子串结束的位置
                if (arr[j + 1][k - 1] && (s.charAt(j) == s.charAt(k))) {
                    arr[j][k] = true;
                    maxLength = i;
                    start = j;
                }
            }
        }
        return s.substring(start,start + maxLength);
    }

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        System.out.println(l.longestPalindrome("ababa"));
    }
}
