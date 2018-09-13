
import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 示例:

 输入："23"
 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinations {
    Map<String,String> map;
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if ("".equals(digits))
            return list;
        String[] arr = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        map = new HashMap<String,String>();
        StringBuffer result = new StringBuffer();
        search(arr,0,digits,result);
        for (String str:map.keySet()) {
            list.add(str);
        }
        return list;
    }
    public void search(String[] arr,int index,String digits,StringBuffer result) {
        if (index == digits.length()) {
            map.put(result.toString(),null);
        } else {
            String str = arr[Integer.valueOf(String.valueOf(digits.charAt(index)))];
            for (int i = 0; i < str.length(); i++) {
                result.append(String.valueOf(str.charAt(i)));
                search(arr,index + 1,digits,result);
                result.deleteCharAt(result.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> list = letterCombinations.letterCombinations("23");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
