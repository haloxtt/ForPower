import java.util.ArrayList;
import java.util.List;

public class Demo6 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");

        // 使用构造器创建一个包含list的列表list1
        List<String> list1 = new ArrayList<String>(list);
        // 使用subList生成与list相同的列表list2
        List<String> list2 = list.subList(0, list.size());
        list2.set(0,"b");
        for (String str:list) {
            System.out.println(str);
        }
    }
}
