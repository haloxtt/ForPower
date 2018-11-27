import org.omg.CORBA.UNKNOWN;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:lambdaTest
 * @author: xietaotao
 * @create: 2018-11-12 15:11
 **/
public class LambdaTest {
    public class Person {

        public Person(String lastName,String firstName,int age) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
        }

        private String lastName;
        private String firstName;
        private int age;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class Program {
        public List<Person> personList() {
            List<Person> persons = Arrays.asList(
                    new Person("Yiming","Zhao",5),
                    new Person("yanggui","li",15),
                    new Person("Chao","Ma",20),
                    new Person("XiaoTian","Zhang",25),
                    new Person("Taoqi","Yun",30),
                    new Person("Xixi","Han",35)
            );
            return persons;
        }
    }

    @FunctionalInterface
    interface MyLambda {
        int doSomeShit(int x,int y);
    }

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        Program program = lambdaTest.new Program();
        List<Person> persons = program.personList();

        //匿名方法
        MyLambda lambda = (x, y) -> x + y;
        System.out.println(lambda.doSomeShit(1,2));
        persons.stream().filter(p -> p.getFirstName().startsWith("Z")).forEach(System.out::println);

        Person p = lambdaTest.new Person("w","c",1);
        Optional<Person> personOpt = Optional.ofNullable(p);
        personOpt.ifPresent(System.out::println);

        Map<String,String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        Map<String,String> result = map.entrySet().stream().filter(c -> "2".equals(c.getValue())).collect(Collectors.toMap(z -> z.getKey(),z -> z.getValue()));
        System.out.println(result.toString());

        Map<String,Person> employeeMap = persons.stream().collect(Collectors.toMap((key->key.getLastName()),(value->value)));

    }
}
