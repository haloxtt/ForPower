/**
 * 罗马数字转整数
 */
public class RomanToInt {
    public int romanToInt(String s) {
        int result = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length - 1; i++) {
            if ("IV".equals(String.valueOf(arr[i]) + String.valueOf(arr[i + 1]))) {
                result += 4;
                arr[i] = 0;
                arr[i + 1] = 0;
            }
            if ("IX".equals(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1)))) {
                result += 9;
                arr[i] = 0;
                arr[i + 1] = 0;
            }
            if ("XL".equals(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1)))) {
                result += 40;
                arr[i] = 0;
                arr[i + 1] = 0;
            }
            if ("XC".equals(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1)))) {
                result += 90;
                arr[i] = 0;
                arr[i + 1] = 0;
            }
            if ("CD".equals(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1)))) {
                result += 400;
                arr[i] = 0;
                arr[i + 1] = 0;
            }
            if ("CM".equals(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1)))) {
                result += 900;
                arr[i] = 0;
                arr[i + 1] = 0;
            }

        }
        into:
        for (int i = 0; i < arr.length; i++) {
            if ("I".equals(String.valueOf(arr[i]))) {
                result += 1;
                continue into;
            }
            if ("V".equals(String.valueOf(arr[i]))) {
                result += 5;
                continue into;
            }
            if ("X".equals(String.valueOf(arr[i]))) {
                result += 10;
                continue into;
            }
            if ("L".equals(String.valueOf(arr[i]))) {
                result += 50;
                continue into;
            }
            if ("C".equals(String.valueOf(arr[i]))) {
                result += 100;
                continue into;
            }
            if ("D".equals(String.valueOf(arr[i]))) {
                result += 500;
                continue into;
            }
            if ("M".equals(String.valueOf(arr[i]))) {
                result += 1000;
                continue into;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        System.out.println(romanToInt.romanToInt("MCMXCIV"));
    }
}
