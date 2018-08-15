import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。

 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 示例 1:
 nums1 = [1, 3]
 nums2 = [2]
 中位数是 2.0
 示例 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 中位数是 (2 + 3)/2 = 2.5
 */
public class GetTwoSortArrayMedian {
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        int[] nums3 = {1,2};
        int[] nums4 = {3,4};
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int min1 = nums1[0];
        int max1 = nums1[nums1.length-1];
        int min2 = nums2[0];
        int max2 = nums2[nums2.length-1];
        double median;//中位数的值
        double medianindex;//中位数的下标
        if (min2 >= max1 || min1 >= max2) {//数组相离状态
            if (min1 >= max2) {//数组相离状态，并且nums1数组较大，交换数组,确保nums2数组较大
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }
            medianindex = ((double)nums1.length+(double)nums2.length)/2;
            double medianposition = medianindex - (double)nums1.length;
            int medianpositionint = new Double(medianposition).intValue();
            if (medianposition > 0) {//中位数位于nums2数组里
                median = (medianposition%2 == 0) ? (nums2[medianpositionint-1]+nums2[medianpositionint]):
                        (nums2[medianpositionint]);//0.5的double转成int会为0，所以取下标时有差异
                return  median;
            } else if (medianposition < 0) {//中位数位于num1数组里
                median = (medianposition%2 == 0) ? (nums1[(int) medianindex-1] + nums1[(int) medianindex])/2 : nums1[(int) medianindex];
                return median;
            } else {//中位数为nums1最后一个和nums2的第一个
                median = new Double(max1+min2)/2;
            }

        } else if ((min2 < max1 && min2 > min1 && max2 > max1) || (min1 < max2 && min1 > min2 && max1 > max2)) {//相交状态
            if (min1 < max2 && min1 > min2 && max1 > max2) {//相交状态，并且num1较大,则交换数组,确保nums2数组较大
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }

        } else{//包含状态
            if (min2 <= min1 && max2 >= max1) {//包含状态，nums2包含nums1,则交换数组，确保是nums1包含nums2的状态

            }
        }
        return 0;
    }
}
