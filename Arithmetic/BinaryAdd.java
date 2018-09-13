/**
 * leetcode 67
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。

 输入为非空字符串且只包含数字 1 和 0。
 示例 1:
 输入: a = "11", b = "1"
 输出: "100"
 */
public class BinaryAdd {
    public String addBinary(String a, String b) {
        int lengb = b.length();
        int lenga = a.length();
        int min = Math.min(lenga,lengb);
        String sbf = "";
        String result = "";
        String newstr = "";
        int plus = 0;
        int i = 0;
        while (i < min || ((i == min) && lenga == lengb)) {
            int sum = Integer.valueOf(String.valueOf(b.charAt(lengb - 1))) + Integer.valueOf(String.valueOf(a.charAt(lenga - 1))) + plus;
            plus = (sum >= 2) ? 1 : 0;
            sbf += (sum % 2);
            i++;

        }
        for (int j = sbf.length() - 1; j >= 0 ; j--) {
            newstr += String.valueOf(sbf.charAt(j));
        }
        if (lenga > lengb)
            sbf = a.substring(0,lenga - min) + newstr;
        if (lengb > lenga)
            sbf = b.substring(0,lengb - min) + newstr;
        return sbf;
    }

    public static void main(String[] args) {
        BinaryAdd addBinary = new BinaryAdd();
        String a = "1";
        String b = "1";
        System.out.println(addBinary.addBinary(a,b));
    }
}
