import java.util.Arrays;

/**
 * @description:88
 * @author: xietaotao
 * @create: 2018-11-23 10:36
 **/
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int last0 = m - 1;
        int last1 = n - 1;
        while (last0 >= 0 && last1 >= 0) {
            if (nums1[last0] > nums2[last1]) {
                nums1[last0 + last1 + 1] = nums1[last0--];
            } else {
                nums1[last0 + last1 + 1] = nums2[last1--];
            }
        }
        if (last1 >= 0) {
            for (int i = 0; i <= last1; i++) {
                nums1[i] = nums2[i];
            }
        }
    }

    public static void main(String[] args) {
        Merge merge = new Merge();
        int[] arr0 = {3,0};
        int[] arr1 = {2};
        int m = 1;
        int n = 1;
        merge.merge(arr0,m,arr1,n);
        System.out.println(Arrays.toString(arr0));
    }
}
