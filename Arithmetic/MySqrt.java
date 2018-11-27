/**
 * @description:69.x的平方根
 * @author: xietaotao
 * @create: 2018-11-14 10:47
 **/
public class MySqrt {
    public int mySqrt(int x) {
        if (x < 1)
            return 0;
        if (x < 4)
            return 1;
        if (x >= 2147395600)
            return 46340;
        int last = 0;
        int lastIndex = 0;
        for (int i = 2; i <= x / 2; i++) {
            int num = i * i;
            if (num == x)
                return i;
            if (num > x && last < x)
                return lastIndex;
            last = num;
            lastIndex = i;
        }
        return lastIndex;
    }

    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        int a = 2147395599;
        int b = 46340 * 46340;
        System.out.println(b);
        System.out.println(mySqrt.mySqrt(a));
    }
}
