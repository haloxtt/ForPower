import java.util.HashMap;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。

 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]
 */
public class TwoSumClass {
    public static void main(String[] args) {
        int[] nums = {2,11, 15,7};
        int target = 9;
        System.out.println(twoSum(nums,target)[0]+"/"+twoSum(nums,target)[1]);
    }
    public static int[] twoSum(int[] nums, int target) {//求两数之和的方法
        int[] result = new int[2];//存放结果的数组
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);//将传入的数组包装成map，K为值，V为下标
        }
        for (int i = 0; i < nums.length; i++) {
            int value = target-nums[i];
            if (map.containsKey(value) && map.get(value) != i) {//如果被减数存在，并且不是自身，则当前下标和map中的另外一个下标即为结果
                result[0] = i;
                result[1] = map.get(value);
            }
        }
        return result;
    }
}
