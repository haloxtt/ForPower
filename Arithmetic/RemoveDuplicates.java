/**
 * @description:26. 删除排序数组中的重复项
 * @author: xietaotao
 * @create: 2018-11-05 16:45
 **/
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int count = 1;
        int recentNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != recentNum) {
                nums[count] = nums[i];
                count++;
                recentNum = nums[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = {0,0};;
        int a = removeDuplicates.removeDuplicates(nums);
        System.out.println(a);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");;
        }
    }
}
