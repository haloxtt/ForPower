import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
 class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        for (int i = 0; i < nums.length; i++) {//冒泡排序
            for (int j = i + 1; j < nums.length; j++) {
                int temp = nums[i];
                if (temp > nums[j]) {
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        int minsum = 99999999;
        for (int i = 1; i < nums.length - 1; i++) {
            int prev = 0;//头指针
            int next = nums.length - 1;//尾指
            while (prev < i && next > i) {
                int sum = nums[i] + nums[prev] + nums[next];
                if (sum == target) { return target;}
                if ((sum > target)) {
                    next--;
                } else {
                    prev++;
                }
                if (Math.abs(sum - target) < Math.abs(minsum - target)) {
                    minsum = sum;
                }
            }
        }
        return minsum;
    }

    public static void main(String[] args) {
        int[] arr = {-3,-2,-5,3,-4};
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(arr,-1));;
    }
}
