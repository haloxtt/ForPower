import java.util.*;
import java.util.logging.SocketHandler;

public class Dnf {

    static List<Weapen> list = new ArrayList<Weapen>();//包袱

    static int currcode;//物品编号

    static Weapen currWeapen;//当前操作装备

    static int shenyuanCount;//深渊次数

    static Random random = new Random();//随机器

    /**
     * 包袱内部类
     */
    class Mypackage {

        public void add(Weapen weapen) {//新增装备
            list.add(weapen);
        }

        public void delete(Weapen weapen) {//删除装备
            list.remove(weapen);
        }

        public void showMypackage() {//显示装备
            int i = 0;
            for(Weapen weapen:list) {
                String name = weapen.getName();//装备名称
                int intensify = weapen.getIntensify();//装备强化数
                System.out.println("*****"+name+"+"+intensify+"*****编号："+i++);
            }
        }
    }

    /**
     * 装备内部类
     */
    static class Weapen {

        //装备名称
        private String name;

        //装备强化数
        private int intensify = 0;

        //物品编号
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getIntensify() {
            return intensify;
        }

        public void setIntensify(int intensify) {
            this.intensify = intensify;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 强化装备的方法
     */
    private static void intensify() {
        int currintensify = currWeapen.getIntensify();//当前强化数值
        if(currintensify >= 0 && currintensify <= 8) {
            currWeapen.setIntensify(currWeapen.getIntensify()+1);
            System.out.println("恭喜您强化"+currWeapen.getName()+"+"+currWeapen.getIntensify()+"成功！");
            intensifyAgain();
        } else if (currintensify >8 && currintensify < 13) {
            int succInt = random.nextInt(10);
            if(succInt > 1 ) {
                currWeapen.setIntensify(currWeapen.getIntensify()+1);
                System.out.println("恭喜您强化"+currWeapen.getName()+"+"+currWeapen.getIntensify()+"成功！");
                intensifyAgain();
            } else {
                System.out.println("强化"+currWeapen.getName()+"+"+currWeapen.getIntensify()+"失败!");
                currWeapen.setIntensify(currWeapen.getIntensify()-2);
                System.out.println("强化数-2，当前强化为+"+currWeapen.getIntensify());
                intensifyAgain();
            }
        } else {
            int succInt = random.nextInt(10);
            if (succInt > 5) {
                currWeapen.setIntensify(currWeapen.getIntensify()+1);
                System.out.println("恭喜您强化"+currWeapen.getName()+"+"+currWeapen.getIntensify()+"成功！");
                intensifyAgain();
            } else {
                list.remove(currWeapen);
                System.out.println("强化失败，装备已经损坏！强化穷三代，增幅毁一生，你咋就不听呢");
            }
        }
    }

    /**
     * 继续强化的方法
     */
    private static void intensifyAgain() {
        System.out.println("继续强化装备请按g+回车，其他键返回主菜单");
        Scanner scan = new Scanner(System.in);
        try {
            String str = scan.next();
            if (!"".equals(str) && ("g".equals(str) || "g".equals(str))) {
                intensify();
            }
        } catch (Exception e) {
            System.out.println("已返回主菜单");
        }
    }

    /**
     *
     * 深渊爆装备的方法
     */
    private static void boom() {
        Random random = new Random();
        int succint = random.nextInt(30);
        Weapen weapen = new Weapen();
        switch (succint) {
            case 0:
                weapen.setName("时光轨迹上衣");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了时光轨迹上衣一件！");
                break;
            case 1:
                weapen.setName("时光轨迹长裤");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了时光轨迹长裤一件！");
                break;
            case 2:
                weapen.setName("时光轨迹靴子");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了时光轨迹靴子一件！");
                break;
            case 3:
                weapen.setName("时光轨迹腰带");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了时光轨迹腰带一件！");
                break;
            case 4:
                weapen.setName("时光轨迹护肩");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了时光轨迹护肩一件！");
                break;
            case 5:
                weapen.setName("时铁马金戈上衣");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了铁马金戈上衣一件！");
                break;
            case 6:
                weapen.setName("铁马金戈长裤");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了铁马金戈长裤一件！");
                break;
            case 7:
                weapen.setName("铁马金戈靴子");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了铁马金戈靴子一件！");
                break;
            case 8:
                weapen.setName("铁马金戈腰带");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了铁马金戈腰带一件！");
                break;
            case 9:
                weapen.setName("铁马金戈护肩");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了铁马金戈护肩一件！");
                break;
            case 10:
                weapen.setName("妖刀村正");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了妖刀村正一件！");
                break;
            case 11:
                weapen.setName("清泉流响");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了清泉流响一件！");
                break;
            case 12:
                weapen.setName("启明星的指引");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了启明星的指引一件！");
                break;
            case 13:
                weapen.setName("氤氲之息");
                weapen.setCode(currcode);
                list.add(weapen);
                System.out.println("金光一闪~~~~~~~，您获得了氤氲之息一件！");
                break;
            default:
                System.out.println("对不起，您充得钱太少，啥也没出");
                currcode--;
        }
        currcode++;
    }

    private static void order(String order) {
        if(!"".equals(order) && ("F".equals(order) || "f".equals(order))) {
            toIntensify();
        }
        if (!"".equals(order) && ("T".equals(order) || "t".equals(order))) {
            shenyuanCount++;
            System.out.println("您进入了时光裂缝地下城...(已肝深渊次数+"+shenyuanCount+"次)");
            boom();
        }
        if (!"".equals(order) && ("D".equals(order) || "d".equals(order))) {
            drop();
        }
        if (!"".equals(order) && ("E".equals(order) || "e".equals(order))) {
            Dnf dnf = new Dnf();
            Mypackage mypackage = dnf.new Mypackage();
            mypackage.showMypackage();
        }
    }

    /**
     * 强化装备的入口
     */
    private static void toIntensify() {
        while (true) {
            Dnf dnf = new Dnf();
            Mypackage mypackage = dnf.new Mypackage();
            mypackage.showMypackage();
            System.out.println("请输入要选择强化的编号(输入其他字符返回主菜单)：");
            Scanner scan = new Scanner(System.in);
            try {
                int i = scan.nextInt();
                if(i >=0 && i < list.size()) {
                    currWeapen = list.get(i);
                    intensify();
                    break;
                } else {
                    System.out.println("您的输入有误");
                }
            } catch (Exception e) {
                System.out.println("您的输入有误");
                break;
            }
        }
    }

    /**
     *
     * 丢弃装备的方法
     */
    private static void drop() {
            Dnf dnf = new Dnf();
            Mypackage mypackage = dnf.new Mypackage();
            mypackage.showMypackage();
            System.out.println("请输入要选择丢弃的装备的编号(一次丢弃多个物品用单个空格隔开，回车结束输入)：");
            Scanner scan = new Scanner(System.in);
            try {
                    String i = scan.nextLine();
                    String[] strArr = i.split(" ");
                    for (int n = list.size()-1;n >= 0;n--) {
                        for (String str:strArr) {
                            if (n == Integer.valueOf(str)) {
                                System.out.println("*****已丢弃"+list.get(n).getName()+"+"+list.get(n).getIntensify()+"*****");
                                list.remove(n);
                                break;
                            }
                        }
                    }
            } catch (Exception e) {
                System.out.println("系统异常，已返回主菜单");
            }
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("*****输入F再回车强化装备，输入T再回车肝深渊,输入D再回车丢弃多余物品,输入E再回车查看背包*****");
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            order(str);
        }
    }
}
