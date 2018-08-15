interface Service {
    void method1();
    void method2();
}
interface ServiceFactory {
    Service getService();
}
class Implementation1 implements Service {
    Implementation1() {
    }
    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }
    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }
}
class Implementation2 implements Service {
    Implementation2() {

    }
    @Override
    public void method1() {
        System.out.println("Implementation2 method1");
    }
    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }
}
class Implementation1Factory implements ServiceFactory {
    public Service getService() {
        return new Implementation1();
    }
}
class Implementation2Factory implements ServiceFactory {
    public Service getService() {
        return new Implementation2();
    }
}
 class Factories {
    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        serviceConsumer(new Implementation1Factory());
        serviceConsumer(new Implementation2Factory());
    }
}