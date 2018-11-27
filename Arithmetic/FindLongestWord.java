import java.util.*;

/**
 * @description:524.
 * @author: xietaotao
 * @create: 2018-11-26 15:06
 **/
public class FindLongestWord {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return o2.length() - o1.length();
                if (o1.charAt(0) != o2.charAt(0))
                    return o1.charAt(0) - o2.charAt(0);
                return 0;
            }
        });
        for (String str : d) {
            int sIndex = 0;
            for (int i = 0; i < s.length() && sIndex < str.length(); i++) {
                if (sIndex == str.length() - 1 && str.charAt(sIndex) == s.charAt(i))
                    return str;
                if (s.charAt(i) == str.charAt(sIndex)) {
                    sIndex++;
                    continue;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("abcd");
        list.add("abcde");
        list.add("bbcd");
        list.add("ab");
        list.add("bb");
        list.add("bcd");
        list.add("bgf");
        list.add("bf");
        list.add("bfga");
        list.add("bc");
        list.add("bc");
        FindLongestWord findLongestWord = new FindLongestWord();
        String a = findLongestWord.findLongestWord("",list);
        System.out.println(a);
    }
}
