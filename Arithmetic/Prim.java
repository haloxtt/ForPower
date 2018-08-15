import java.util.ArrayList;
import java.util.List;

public class Prim {
    private static int[][] graph;
    final static int MAX = 1000;//不通顶点的默认值
    static int vertexSize = 0;//顶点数量
    /**边集数组*/
    private static List<Edge> edgeList = new ArrayList<Edge>();//边集数组
    private static void graphInit() {
        graph = new int[][]{
                {0,10,MAX,MAX,MAX,11,MAX,MAX,MAX},
                {10,0,18,MAX,MAX,MAX,16,MAX,12},
                {MAX,MAX,0,22,MAX,MAX,MAX,MAX,8},
                {MAX,MAX,22,0,20,MAX,MAX,16,21},
                {MAX,MAX,MAX,20,0,26,MAX,7,MAX},
                {11,MAX,MAX,MAX,26,0,17,MAX,MAX},
                {MAX,16,MAX,MAX,MAX,17,0,19,MAX},
                {MAX,MAX,MAX,16,7,MAX,19,0,MAX},
                {MAX,12,8,21,MAX,MAX,MAX,MAX,0}
        };
        vertexSize = graph[0].length;
    }

    public static void main(String[] args) {
        graphInit();
        startPrim3();
        createEdages();
        kruskal();

    }

    /**
     * 创建边集数组，并且对他们按权值从小到大排序（顺序存储结构也可以认为是数组吧）
     */
    public static void createEdages() {

        Edge v0 = new Edge(4, 7, 7);
        Edge v1 = new Edge(2, 8, 8);
        Edge v2 = new Edge(0, 1, 10);
        Edge v3 = new Edge(0, 5, 11);
        Edge v4 = new Edge(1, 8, 12);
        Edge v5 = new Edge(3, 7, 16);
        Edge v6 = new Edge(1, 6, 16);
        Edge v7 = new Edge(5, 6, 17);
        Edge v8 = new Edge(1, 2, 18);
        Edge v9 = new Edge(6, 7, 19);
        Edge v10 = new Edge(3, 4, 20);
        Edge v11 = new Edge(3, 8, 21);
        Edge v12 = new Edge(2, 3, 22);
        Edge v13 = new Edge(3, 6, 24);
        Edge v14 = new Edge(4, 5, 26);

        edgeList.add(v0);
        edgeList.add(v1);
        edgeList.add(v2);
        edgeList.add(v3);
        edgeList.add(v4);
        edgeList.add(v5);
        edgeList.add(v6);
        edgeList.add(v7);
        edgeList.add(v8);
        edgeList.add(v9);
        edgeList.add(v10);
        edgeList.add(v11);
        edgeList.add(v12);
        edgeList.add(v13);
        edgeList.add(v14);
    }

    private static void startPrim() {
        int[] lowcost = new int[vertexSize];//最小代价权值的数组,为0表示已经是最小的了
        int[] adjvex = new int[vertexSize];//放顶点的权值
        int min,minId,sum = 0;
        for (int i = 1; i < vertexSize; i++) {//相当于拿到第一排的数据
            lowcost[i] = graph[0][i];
        }
        for (int i = 1; i < vertexSize; i++) {
            min = MAX;
            minId = 0;
            for (int j = 1; j < vertexSize; j++) {//遍历当前一排的数据，找到最小的值
                if (lowcost[j] < min && lowcost[j] > 0){
                    min = lowcost[j];
                    minId = j;
                }
            }
            System.out.println("顶点："+adjvex[minId]+"权值"+min);
            sum += min;
            lowcost[minId] = 0;
            for (int j = 1; j < vertexSize; j++) {//遍历数组中的某一排，确认是否可以加入最小权值数组
                if (lowcost[j] != 0 && graph[minId][j] < lowcost[j]) {
                    lowcost[j] = graph[minId][j];
                    adjvex[j] = minId;
                }
            }
        }
        System.out.println("最小权值之和为："+sum);
    }
    static class Edge {

        private int begin;
        private int end;
        private int weight;

        public Edge(int begin, int end, int weight) {
            super();
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge [begin=" + begin + ", end=" + end + ", weight=" + weight + "]";
        }



    }

    /**
     * 克鲁斯卡尔算法
     */
    public static void kruskal() {
        int[] parent = new int[vertexSize];
        int m,n;
        int sum = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge e = edgeList.get(i);
            m = find(parent,e.getBegin());
            n = find(parent,e.getEnd());
            if (m!=n) {
                parent[m] = n;
                sum += e.getWeight();
                System.out.println("起点为："+e.getBegin()+",终点为："+e.getEnd()+",权值为："+e.getWeight());
            }
        }
        System.out.println("最小权值为："+sum);
    }
    public static int find(int[] parent,int f) {
        if (parent[f] > 0) {
            f = find(parent,parent[f]);
        }
        return f;
    }
    private static void startPrim1() {
        int[] lowcost = new int[vertexSize];//最小代价权值的数组,为0表示已经是最小的了
        int[] adjvex = new int[vertexSize];//放顶点的权值
        int min,minId,sum = 0;
        for (int i = 1; i < vertexSize; i++) {
            lowcost[i] = graph[0][i];//
        }
        for (int i = 1; i < vertexSize; i++) {
            min = MAX;
            minId = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowcost[j] != 0 && lowcost[j] < min) {
                    min = lowcost[j];
                    minId = j;
                }
            }
            System.out.println("访问到了顶点："+adjvex[minId]+"权值为"+min);
            sum += min;
            lowcost[minId] = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (graph[minId][j] < lowcost[j] && graph[minId][j] != 0) {
                    lowcost[j] = graph[minId][j];
                    adjvex[j] = minId;
                }
            }
        }
        System.out.println("最小权值之和为："+sum);
    }
    private static void startPrim3() {
        int[] lowcost = new int[vertexSize];//存放最小顶点的数组
        int[] vertexarr = new int[vertexSize];//存放顶点的数组
        int sum = 0;
        int min,minId;
        for (int i = 1; i < vertexSize; i++) {
            lowcost[i] = graph[0][i];//先把lowcost初始化为第一排
        }
        for (int i = 1; i < vertexSize; i++) {//遍历找出当前lowcost最小的
            min = MAX;
            minId = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowcost[j] < min && lowcost[j] != 0) {
                    min = lowcost[j];
                    minId = j;
                }
            }
            System.out.println("最小权值为:"+min+"顶点为"+vertexarr[minId]);
            sum += min;
            lowcost[minId] = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowcost[j] > graph[minId][j] && graph[minId][j] != 0) {
                    lowcost[j] = graph[minId][j];
                    vertexarr[j] = minId;
                }
            }

        }
        System.out.println("最小权值和为："+sum);
    }
}
