/**
 * 最小公倍数
 */
public class Lcm {
    public static void main(String[] args) {
        int n,m;
        n = 9;
        m = 6;
        int gcd = gcd(n,6);
        int lcm = n * m / gcd;
        System.out.println("m"+m+"和n的最小公倍数是"+lcm);
        System.out.println(ss(20));
    }

    /**
     * 求最大公约数
     * @param m
     * @param n
     * @return
     */
    private static int gcd(int m,int n) {
        return (m % n == 0) ? n : gcd(n,m % n);
    }
    /**
     *
     */
    private static int ss(int n) {
        return (n != 1) ? n * ss(n-1) : 1;
    }
}
