/**
 * 策略设计模式
 */
public class Demo5 {
    String name() {
        return getClass().getSimpleName();
    }
    Object process(Object input) {
        return input;
    }
}
class Upcase extends Demo5 {
    String process(Object input) {
        return ((String)input).toUpperCase();
    }
}
class Downcase extends Demo5 {
    String process(Object input) {
        return ((String)input).toLowerCase();
    }
}
class Apply {
    public static void process(Demo5 p,Object s) {
        System.out.println("Using:"+p.name());
        System.out.println(p.process(s));
    }
    public static String s = "Disagreement with beliefs is by definition in correct";

    public static void main(String[] args) {
        process(new Upcase(),s);
        process(new Downcase(),s);
    }
}