class OuterClass {
    class InsideClass {

    }
    public  InsideClass getInside() {
        return new InsideClass();
    }
    public static void main(String[] args) {
        OuterClass q = new OuterClass();
        OuterClass.InsideClass p = q.getInside();
        InsideClass y = q.getInside();
    }

}
class test {
    public static void main(String[] args) {
        OuterClass q =  new OuterClass();
        OuterClass.InsideClass y = q.getInside();
        OuterClass.InsideClass y1 = q.new InsideClass();//注意new后面不能加OuterClass.
    }
}
