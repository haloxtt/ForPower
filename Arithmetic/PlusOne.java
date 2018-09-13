import com.sun.tools.corba.se.idl.constExpr.Plus;

import java.util.Arrays;

/**
 * leetCode 66
 *
 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。

 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

 你可以假设除了整数 0 之外，这个整数不会以零开头。

 示例 1:

 输入: [1,2,3]
 输出: [1,2,4]
 解释: 输入数组表示数字 123。
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int last = digits.length - 1;
        digits[last] += 1;
        for (;last > 0; last--) {
            if (digits[last] <= 9)
                return digits;
            digits[last] = digits[last] % 10;
            digits[last - 1] += 1;
        }
        if (digits[0] > 9) {
            int[] arr = new int[digits.length + 1];
            arr[0] = 1;
            digits[0] = digits[0] % 10;
            System.arraycopy(digits,0,arr,1,digits.length);
            return arr;
        }
        return digits;

    }
    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] arr = {9,9,9,9,9,9};
        System.out.println(Arrays.toString(plusOne.plusOne(arr)));
    }
}
