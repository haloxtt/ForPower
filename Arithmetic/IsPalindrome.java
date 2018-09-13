/**
 * 是否是回文数
 */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        int temp = x;
        int s = 0;
        while (x > 0) {
            s = s * 10 + x % 10;
            x = x / 10;
        }
        return s == temp;
    }

    public static void main(String[] args) {
        IsPalindrome is = new IsPalindrome();
        System.out.println(is.isPalindrome(0));
        System.out.println(is.isPalindrome(121));
        System.out.println(is.isPalindrome(-121));
    }
}
