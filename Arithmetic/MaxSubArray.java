/**
 * leetcode  53
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(sum[i - 1] + nums[i],nums[i]);
            if (sum[i] > max)
                max = sum[i];
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] arr = {1,2};
        System.out.println(maxSubArray.maxSubArray(arr));
    }
}
