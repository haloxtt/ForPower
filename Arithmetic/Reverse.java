import java.math.BigDecimal;

/**
 * 整数反转
 */
public class Reverse {
    public int reverse(int x) {
        boolean neactive = x < 0;//是否为负数
        long result = 0;//返回结果
        if (neactive)
            x = -x;
        while (x > 0) {
            result = x % 10 + result * 10;
            x = x / 10;
        }
        if (neactive)
            result = -result;
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE)
            return 0;
        return Integer.parseInt(String.valueOf(result));
    }

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int result = reverse.reverse(-1999999999);
        System.out.println(result);
    }
}