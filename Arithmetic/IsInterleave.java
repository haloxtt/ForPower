/**
 * @description:97，交错字符串
 * @author: xietaotao
 * @create: 2018-11-14 14:22
 **/
public class IsInterleave {

    public boolean isInterleave(String s1, String s2, String s3) {
        if ("".equals(s1) && s2.equals(s3))
            return true;
        if ("".equals(s1) && !s2.equals(s3))
            return false;
        if ("".equals(s2) && s1.equals(s3))
            return true;
        if ("".equals(s2) && !s1.equals(s3))
            return false;
        int s1Index = 0,s2Index = 0;
        int length = Math.min(s1.length(),s1.length());
        for (int i = 0; s1Index <= length && s2Index <= length; i++) {
            if (s3.charAt(i) == s1.charAt(s1Index)) {
                s1Index++;
                continue;
            }
            if (s3.charAt(i) == s2.charAt(s2Index)) {
                s2Index++;
                continue;
            }
            return false;
        }
        if (s3.substring(s1Index + s2Index,s3.length()).equals(s1.substring(length,s1.length()) + s2.substring(length,s2.length())))
            return true;
        return false;
    }

    public static void main(String[] args) {
        IsInterleave isInterleave = new IsInterleave();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        String s4 = "aabccaaa";
        String s5 = "aaa";
        System.out.println(isInterleave.isInterleave(s1,s2,s3));
    }
}
