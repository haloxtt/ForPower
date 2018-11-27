import java.util.ArrayList;
import java.util.List;

/**
 * @description:118.杨辉三角
 *
 * @author: xietaotao
 * @create: 2018-11-15 11:20
 **/

public class Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i) {
                    list.add(1);
                } else {
                    List<Integer> last = lists.get(i - 2);
                    int temp = last.get(j - 2) + last.get(j - 1);
                    list.add(temp);
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        Generate generate = new Generate();
        System.out.println(generate.generate(10).toString());
    }

}
