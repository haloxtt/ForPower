import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
        }
    }
}
