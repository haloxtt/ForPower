/**
 * @description:680.
 * @author: xietaotao
 * @create: 2018-11-22 18:26
 **/
public class ValidPalindrome {
    public boolean validPalindrome(String s) {
        int i = -1;
        int j = s.length();
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s,i + 1,j) || isPalindrome(s,i,j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s,int i,int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        String a = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        System.out.println(validPalindrome.validPalindrome(a));
    }
}
