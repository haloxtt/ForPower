public class Demo1 {
    public static void main(String[] args) {
        Demo1 d = new Demo1();
        staticPrint(d);
    }
    private static  void staticPrint(Demo1 d) {
        System.out.println("2");
        d.print();
    }
    private void print() {
        System.out.println("1");
    }
}
