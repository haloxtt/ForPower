/**
 * 约瑟夫杀人法
 */
public class MyJosephus {
    public static int N = 20;//人数
    public static int M = 5;//数到几被咔嚓

    class Node{
        int val;//下标
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public void killNode() {
        Node header = new Node(1);//第一个节点
        Node x = header;//目前被点到的人
        for (int i = 2; i <= N; i++) {//初始化工作
            x = (x.next = new Node(i));
        }
        x.next = header;//头尾相接
        System.out.println("被咔嚓的顺序为：");
        while (x != x.next) {
            //至少还有两个人
            for (int i = 1; i < M; i++) {
                x = x.next;
            }
            System.out.println(x.next.val + "被咔嚓");
            x.next = x.next.next;
        }
        System.out.println("最后的幸运儿是" + x.val);
    }

    public static void main(String[] args) {
        MyJosephus myJosephus = new MyJosephus();
        myJosephus.killNode();
    }
}
