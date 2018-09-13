/**
 * 大数相乘（普通解法）
 */

public class MyBigCount {
    public static void main(String[] args) {
        String str1 = "25";
        String str2 = "25";
        int len1 = str1.length();
        int len2 = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        covertdata(s1,len1);
        covertdata(s2,len2);
        System.out.println("乘数" + str1);
        System.out.println("乘数" + str2);
        multiply(s1,len1,s2,len2);

    }
    private static void covertdata(char[] data,int len) {
        //高低位对调
        for (int i = 0; i < len / 2; i++) {
            data[i] += data[len - 1 - i];//通过对位之和换位置
            data[len - 1 - i] = (char)(data[i] - data[len - 1 -i]);
            data[i] = (char)(data[i] - data[len - 1 -i]);
        }
    }
    private static void multiply(char[] a,int alen,char[] b,int blen) {
        //两位数的乘积不会超过乘数位数相加+3位
        int csize = alen + blen +3;
        //乘积数组
        int[] c = new int[csize];
        //对齐逐位相乘
        for (int i = 0; i < alen; i++) {
            for (int j = 0; j < blen; j++) {
                c[i + j] += Integer.parseInt(String.valueOf(a[i])) * Integer.parseInt(String.valueOf(b[j]));
            }
        }
        int m = 0;
        //进位处理
        for (int i = 0; i < csize; i++) {
            int carry = c[i] / 10;
            if (carry > 0) {
                c[i] = c[i] % 10;
                c[i + 1] += carry;
            }
        }
        // 找到最高位
        for (m = csize - 1; m >= 0;) {
            if (c[m] > 0)
                break;
            m--;
        }
        // 由最高位开始打印乘积
        System.out.print("乘积");
        for (int n = 0; n <= m; n++) {
            System.out.print(c[m - n]);
        }
        System.out.println("");
    }
}

