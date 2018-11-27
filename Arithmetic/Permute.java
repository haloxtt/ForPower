import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:46. 全排列
 * @author: xietaotao
 * @create: 2018-11-05 17:04
 **/
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        Map<Integer,Boolean> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],true);
        }
        return null;
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = {1,2,3};
        List<List<Integer>> list = permute.permute(nums);
        for (List l : list) {
            System.out.println(l.toString());
        }
    }
}
