//class Factory {
//}
//class Impl1 implements Service {
//    private Impl1(){}
//    @Override
//    public void method2() {
//        System.out.println("impl1 method2");
//    }
//    public static ServiceFactor1 factor1 = new ServiceFactor1() {
//        @Override
//        public Service getService() {
//            return new Impl1();
//        }
//    };
//    @Override
//    public void method1() {
//        System.out.println("impl1 method1");
//    }
//}
//class Impl2 implements Service {
//    private Impl2(){}
//    @Override
//    public void method2() {
//        System.out.println("impl2 method2");
//    }
//
//    @Override
//    public void method1() {
//        System.out.println("impl2 method1");
//    }
//    public static ServiceFactor1 factor1= new ServiceFactor1() {
//        @Override
//        public Service getService() {
//            return new Impl2();
//        }
//    };
//}
//class Factories {
//    public static void serviceConsumer(ServiceFactory fact) {
//        Service s = fact.getService();
//        s.method2();
//        s.method1();
//    }
//
//    public static void main(String[] args) {
//        serviceConsumer(Impl1.factor1);
//        serviceConsumer(Impl2.factor1);
//
//    }
//}
