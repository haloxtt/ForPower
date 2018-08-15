import java.util.ArrayList;

class Plate {
    Plate(int i) {
        System.out.println("Plate 构造函数");
    }
}
class DinnerPlate extends Plate {
    DinnerPlate(int i) {
        super(i);
        System.out.println("DinnerPlate 构造函数");
    }
}
class Utensil {
    Utensil(int i) {
        System.out.println("Utensil 构造函数");
    }
}
class Spoon extends Utensil {
    Spoon(int i) {
        super(i);
        System.out.println("Spoon 构造函数");
    }
}
class Fork extends Utensil {
    Fork(int i) {
        super(i);
        System.out.println("Fork 构造函数");
    }
}
class Knife extends Utensil {
    Knife(int i) {
        super(i);
        System.out.println("Knife 构造函数");
    }
}
class Custom {
    Custom(int i) {
        System.out.println("Custom 构造函数");
    }
}
class PlaceSetting extends Custom {
    private Spoon sp;
    private Fork frk;
    private Knife kn;
    private DinnerPlate pl;
    public PlaceSetting(int i) {
        super(i+1);
        sp = new Spoon(i+2);
        frk = new Fork(i+3);
        kn = new Knife(i+4);
        pl = new DinnerPlate(i+5);
        System.out.println("PlaceSetting 构造函数");
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        PlaceSetting x = new PlaceSetting(9);
    }

}
