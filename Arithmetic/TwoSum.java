import java.util.Arrays;

/**
 * @description:167.找到和为target的两个数
 * @author: xietaotao
 * @create: 2018-11-21 10:35
 **/
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int[] arr = new int[2];
        int big = numbers.length - 1;
        int small = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[big] + numbers[small] == target) {
                arr[0] = small + 1;
                arr[1] = big + 1;
                return arr;
            } else if(numbers[big] + numbers[small] > target) {
                big--;
            } else {
                small++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7};
        int target = 4;
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(arr,target)));
    }
}
