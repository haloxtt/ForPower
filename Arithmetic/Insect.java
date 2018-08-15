class Insect {
    private int i = 9;
    protected int j;
    Insect() {
        System.out.println("i="+i+",j="+j);
        j = 39;
    }
    private static int x1 = printInt("static Insect.x1 initialized");
    static int printInt(String s) {
        System.out.println(s);
        return 47;
    }
}
class  Beetle extends Insect {
    private int k = printInt("Beetle.k initialized");
    public Beetle() {
        System.out.println("k="+k);
        System.out.println("j="+j);
    }
    private static int x2 = printInt("static Beetle.x2 initialized");
    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        Beetle b = new Beetle();
    }
}
