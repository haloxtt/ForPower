import java.util.Arrays;

public class JustForFun {
    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    for (int l = 1; l < 5; l++) {
                        for (int m = 1; m < 5; m++) {
                            for (int n = 1; n < 5; n++) {
                                for (int o = 1; o < 5; o++) {
                                    for (int p = 1; p < 5; p++) {
                                        for (int q = 1; q < 5; q++) {
                                            for (int r = 1; r < 5; r++) {
                                                int[] arr = {i,j,k,l,m,n,o,p,q,r};
                                                    if(get2(arr)) {
                                                        if(get4(arr)) {
                                                            if(get5(arr)) {
                                                                if(get6(arr)) {
                                                                    if(get7(arr)) {
                                                                        if(get8(arr)) {
                                                                            if(get9(arr)) {
                                                                                if(get10(arr)) {
                                                                                    printAnswer(arr);
                                                                                    return;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static boolean get2(int[] arr) {
        if(arr[1]==1 && arr[4]==3)
            return true;
        if(arr[1]==2 && arr[4]==4)
            return true;
        if(arr[1]==3 && arr[4]==1)
            return true;
        if(arr[1]==4 && arr[4]==2)
            return true;
        return false;
    }
    private static boolean get3(int[] arr) {
        if(arr[2]==1 && arr[5]==arr[1] && arr[1]==arr[3] && arr[3]!=arr[2])
            return true;
        if(arr[2]==2 && arr[2]==arr[1] && arr[1]==arr[3] && arr[3]!=arr[5])
            return true;
        if(arr[2]==3 && arr[2]==arr[5] && arr[5]==arr[3] && arr[3]!=arr[1])
            return true;
        if(arr[2]==4 && arr[2]==arr[5] && arr[5]==arr[1] && arr[1]!=arr[3])
            return true;
        return false;
    }
    private static boolean get4(int[] arr) {
        if(arr[3]==1 && arr[0]==arr[4])
            return true;
        if(arr[3]==2 && arr[1]==arr[6])
            return true;
        if(arr[3]==3 && arr[0]==arr[8])
            return true;
        if(arr[3]==4 && arr[5]==arr[9])
            return true;
        return false;
    }
    private static boolean get5(int[] arr) {
        if(arr[4]==1 && arr[7]==1)
            return true;
        if(arr[4]==2 && arr[3]==2)
            return true;
        if(arr[4]==3 && arr[8]==3)
            return true;
        if(arr[4]==4 && arr[6]==4)
            return true;
        return false;
    }
    private static boolean get6(int[] arr) {
        if(arr[5]==1 && arr[1]==arr[3] && arr[3]==arr[7])
            return true;
        if(arr[5]==2 && arr[0]==arr[5] && arr[5]==arr[7])
            return true;
        if(arr[5]==3 && arr[2]==arr[9] && arr[9]==arr[7])
            return true;
        if(arr[5]==4 && arr[4]==arr[8] && arr[8]==arr[7])
            return true;
        return false;
    }
    private static boolean get7(int[] arr) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for(int t:arr) {
            switch (t) {
                case 1:
                    countA++;
                    break;
                case 2:
                    countB++;
                    break;
                case 3:
                    countC++;
                    break;
                case 4:
                    countD++;
                    break;

            }
        }
        int[] sort = {countA,countB,countC,countD};
        Arrays.sort(sort);
        if(arr[6]==1 && sort[0]==countC)
            return true;
        if(arr[6]==2 && sort[0]==countB)
            return true;
        if(arr[6]==3 && sort[0]==countA)
            return true;
        if(arr[6]==4 && sort[0]==countD)
            return true;
        return false;
    }
    private static boolean get8(int[] arr) {
        if(arr[7]==1 && (arr[6]-arr[0]!=1 && arr[6]-arr[0]!=-1))
            return true;
        if(arr[7]==2 && (arr[4]-arr[0]!=1 && arr[4]-arr[0]!=-1))
            return true;
        if(arr[7]==3 && (arr[1]-arr[0]!=1 && arr[1]-arr[0]!=-1))
            return true;
        if(arr[7]==4 && (arr[9]-arr[0]!=1 && arr[9]-arr[0]!=-1))
            return true;
        return false;
    }
    private static boolean get9(int[] arr) {
        if(!(arr[0]==arr[5]) == (arr[5]==arr[4]) && arr[8]==1)
            return true;
        if(!(arr[0]==arr[5]) == (arr[9]==arr[4]) && arr[8]==2)
            return true;
        if(!(arr[0]==arr[5]) == (arr[1]==arr[4]) && arr[8]==3)
            return true;
        if(!(arr[0]==arr[5]) == (arr[8]==arr[4]) && arr[8]==4)
            return true;
        return false;
    }
    private static boolean get10(int[] arr) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for(int t:arr) {
            switch (t) {
                case 1:
                    countA++;
                    break;
                case 2:
                    countB++;
                    break;
                case 3:
                    countC++;
                    break;
                case 4:
                    countD++;
                    break;

            }
        }
        int[] sort = {countA,countB,countC,countD};
        Arrays.sort(sort);
        if(arr[9]==1 && sort[3]-sort[0]==3)
            return true;
        if(arr[9]==2 && sort[3]-sort[0]==2)
            return true;
        if(arr[9]==3 && sort[3]-sort[0]==4)
            return true;
        if(arr[9]==4 && sort[3]-sort[0]==1)
            return true;
        return false;
    }
    private static void printAnswer(int[] arr) {
        for (int i:arr) {
            switch (i) {
                case 1:
                    System.out.println("A");
                    break;
                case 2:
                    System.out.println("B");
                    break;
                case 3:
                    System.out.println("C");
                    break;
                case 4:
                    System.out.println("D");
                    break;

            }
        }
    }

}
