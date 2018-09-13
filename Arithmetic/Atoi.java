/**
 * 实现 atoi，将字符串转为整数。
 */
public class Atoi {
    public int myAtoi(String str) {
    char[] arr = str.toCharArray();
    int flag = 1;//是否为负数的标识
        boolean z = false;//是否已经有符号的标识
    for (int i = 0; i < arr.length; i++) {
        if (("+".equals(String.valueOf(arr[i])))) {
            if (z)
                return 0;
            z = true;
            continue;
        }
        if (("-".equals(String.valueOf(arr[i])))) {
            if (z)
                return 0;
            z = true;
            flag *= -1;
            continue;
        }
            if ((arr[i] >= 48 && arr[i] <=57)) {//找到第一个为数字的字符
                for (int j = i; j < arr.length; j++) {
                    if ((arr[j] < 48 || arr[j] > 57) && !("-".equals(String.valueOf(arr[i])))
                            && !("+".equals(String.valueOf(arr[i]))) || (j == arr.length - 1)) {
                        String substr = (j == arr.length - 1) ? str.substring(i,j + 1) : str.substring(i,j);
                        if ((j == arr.length - 1) && (arr[j] < 48 || arr[j] > 57)) {
                            substr = substr.substring(0,substr.length() - 1);
                        }
                        if ("+".equals(substr) || "-".equals(substr)) {
                            return 0;
                        }
                        int k = 0;
                        int sublength = substr.length();
                        for (; k < substr.length(); k++) {
                            if (substr.charAt(k) != 48) {
                                substr = substr.substring(k,substr.length());
                                break;
                            }
                        }
                        if (k == sublength) {
                            return 0;
                        }
                        if (substr.length() > 11) {
                           return (flag == -1)?Integer.MIN_VALUE:Integer.MAX_VALUE;
                        }
                        long result = Long.parseLong(substr);
                        result = (flag == -1) ? -result : result;
                        if (result > Integer.MAX_VALUE)
                            return Integer.MAX_VALUE;
                         if (result < Integer.MIN_VALUE)
                            return Integer.MIN_VALUE;
                        return Integer.parseInt(String.valueOf(result));
                    }
                }
            } else {
                if (z) {
                    return 0;
                }
            }
            if (arr[i] != 32) {
                return 0;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("-   234"));
    }
}
