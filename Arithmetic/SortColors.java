import java.util.Arrays;

/**
 * @description:75. 颜色分类
 * @author: xietaotao
 * @create: 2018-11-06 18:28
 **/
public class SortColors {
    public void sortColors(int[] nums) {
        int high = nums.length - 1;
        int low = 0;
        for (int i = 0; i <= high;) {
            if (nums[i] == 0) {
                swap(nums,low++,i++);
            } else if (nums[i] == 2) {
                swap(nums,high--,i);
            } else {
                i++;
            }
        }
    }

    public void swap(int[] arr,int a,int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        int[] arr = {0,1,1,2,1,0,2,1,2,2,1,0,1,1,2,2,0};
        sortColors.sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
}
