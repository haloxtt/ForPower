/**
 * 最大公约数算法
 */
public class Gcd {
    public static void main(String[] args) {
        int n,m;
        m = 9;
        n = 6;
        System.out.println(gcd(m,n));
    }
    private static int gcd(int m,int n) {
        return (m % n == 0) ? n : gcd(n,m % n);
    }
}
