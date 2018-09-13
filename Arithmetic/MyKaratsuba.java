/**
 * 卡拉楚巴算法karatsuba
 */
public class MyKaratsuba {
    public static void main(String[] args) {
        long l1 = 2544444444445L;
        long l2 = 3235L;
        System.out.println(karatsuba(l1,l2));
    }
    private static long karatsuba(long num1,long num2) {
        if (num1 < 10 || num2 < 10)
            return num1 * num2;

        //计算拆分长度
        int size1 = String.valueOf(num1).length();
        int size2= String.valueOf(num2).length();
        int half = Math.max(size1,size2) / 2;

        //拆分为a,b,c,d四部分
        long a = Long.valueOf(String.valueOf(num1).substring(0,size1 - half));
        long b = Long.valueOf(String.valueOf(num1).substring(size1 - half));
        long c = Long.valueOf(String.valueOf(num2).substring(0,size2 - half));
        long d = Long.valueOf(String.valueOf(num2).substring(size2 - half));

        //计算z2,z0,z1，此处的乘法使用递归
        long z2 = karatsuba(a,c);
        long z0 = karatsuba(b,d);
        long z1 = karatsuba((a + b),(c + d)) - z0 -z2;
        return (long)(z2 * Math.pow(10,(2 * half)) + z1 * Math.pow(10,half) + z0);
    }
}
