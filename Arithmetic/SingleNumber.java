/**
 * @description:
 * @author: xietaotao
 * @create: 2018-11-14 17:58
 **/
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,2,1,2};
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(arr));
    }
}
