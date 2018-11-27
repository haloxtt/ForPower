import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

/**
 * @description:633
 * @author: xietaotao
 * @create: 2018-11-21 15:11
 **/
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        int a = 0,b = (int)Math.sqrt(c);
        while (a <= b) {
            int temp = a * a + b * b;
            if (temp == c) {
                return true;
            } else if (temp > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JudgeSquareSum judgeSquareSum = new JudgeSquareSum();
        int c = 1000000000;
        System.out.println(judgeSquareSum.judgeSquareSum(c));
    }
}
