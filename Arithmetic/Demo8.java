import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo8 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
        for (int i:list) {
            System.out.println(i);
        }
    }
}
