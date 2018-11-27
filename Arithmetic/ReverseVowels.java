import java.util.Arrays;

/**
 * @description:345."leetcode"
    输出: "leotcede"
 * @author: xietaotao
 * @create: 2018-11-21 17:41
 **/
public class ReverseVowels {
    public String reverseVowels(String s) {
        if (s.length() <= 1)
            return s;
        char[] arr = s.toCharArray();
        int pre = 0;
        int next = s.length() - 1;
        while (pre < next) {
            if (isVowels(arr[pre])) {
                for (int i = next; i > pre; i--) {
                    if (isVowels(arr[i])) {
                        char temp = arr[i];
                        arr[i] = arr[pre];
                        arr[pre] = temp;
                        next = i - 1;
                        break;
                    }
                }
            }
            pre++;
        }
        return String.valueOf(arr);
    }

    private boolean isVowels(char a) {
        if (a == 97 || a == 101 || a == 105 || a == 111 || a == 117 || a == 65 || a == 69 || a == 73 || a == 79 || a == 85)
            return true;
        return false;
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        String a = "A man, a plan, a canal: Panama";
        String b = "a man, a plan, a canal: PanamA";
        System.out.println(reverseVowels.reverseVowels(a));
        char c = 'c';
        boolean[] arr = new boolean[126];
        arr['c'] = true;
        System.out.println(arr[c]);
    }
}
