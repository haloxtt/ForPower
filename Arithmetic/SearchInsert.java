/**
 * leetcode 35
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

 你可以假设数组中无重复元素。

 示例 1:

 输入: [1,3,5,6], 5
 输出: 2
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        if (length == 0)
            return 0;
        if (nums[length - 1] < target)
            return length;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] >= target)
                return i;
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        SearchInsert searchInsert = new SearchInsert();
        int[] arr = {1,5,7,8,9};
        int target = 10;
        System.out.println(searchInsert.searchInsert(arr,target));
    }
}
