/**
 * 棋盘覆盖算法
 */
public class ChessBoard {
    int[][] board = new int[4][4];//表示4x4的棋盘
    int tile = 1;//表示L型骨牌的编号
    //处理带有特殊棋子的棋盘，tr，tc表示棋盘的入口，既棋盘左上角的的行列号，dr，dc表示特殊棋子的行列位置，size表示棋盘的大小
    public void chessBoard(int tr,int tc,int dr,int dc,int size) {
        if (size == 1) {
            return;
        }
        int t = tile++;
        System.out.println(t);
        int s = size / 2;//每次划分为原来的一半
        //step1，处理左上棋盘
        if (dr < tr + s && dc < tc + s) {//左上角棋盘有特殊棋子
            chessBoard(tr,tc,dr,dc,s);//处理左上角的特殊棋子
        } else {
            board[tr + s -1][tc + s -1] = t;//设左上角子棋盘的右下角为特殊棋子，用t型的骨牌覆盖。
            chessBoard(tr,tc,tr + s -1,tc + s -1,s);//处理有用骨牌覆盖的格子作为特殊棋子的左上角子棋盘
        }
        //step2，处理右上角棋盘
        if (dr < tr + s && dc >= tc +s) {//右上角子棋盘有特殊棋子
            chessBoard(tr,tc + s,dr,dc,s);//处理有特殊棋子的右上角子棋盘
        } else {
            board[tr + s - 1][tc + s] = t;//设右上角子棋盘的左下角为特殊棋子
            chessBoard(tr,tc + s,tr + s - 1,tc + s,s);//处理有用骨牌覆盖的格子作为特殊棋子的右上角子棋盘
        }
        //step3,处理右下角棋盘
        if (dr >= tr + s && dc < tc + s) {//左下角子棋盘有特殊棋子
            chessBoard(tr + s,tc,dr,dc,s );
        } else {
            board[tr + s][tc + s - 1] = t;
            chessBoard(tr + s,tc,tr + s,tc + s -1,s);
        }
        //step4 ,处理右下角棋盘
        if (dr >= tr + s && dc >= tc + s) {
            chessBoard(tr + s,tc + s,dr,dc,s);
        } else {
            board[tr + s][tc + s] = t;
            chessBoard(tr + s,tc + s,tr + s,tc +s,s);
        }
    }
    public static void main(String[] args)
    {
        ChessBoard c = new ChessBoard();
        c.chessBoard(0,0,1,1,4);
        for(int i = 0; i <4; i++)
        {
            for(int j = 0; j <4; j++) {
                System.out.print(c.board[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
