public class MyDijkstra {

    private static int N = 1000;
    private static int[][] Graph = {
            {0, 1, 5, N, N, N, N, N, N},
            {1, 0, 3, 7, 5, N, N, N, N},
            {5, 3, 0, N, 1, 7, N, N, N},
            {N, 7, N, 0, 2, N, 3, N, N},
            {N, 5, 1, 2, 0, 3, 6, 9, N},
            {N, N, 7, N, 3, 0, N, 5, N},
            {N, N, N, 3, 6, N, 0, 2, 7},
            {N, N, N, N, 9, 5, 2, 0, 4},
            {N, N, N, N, N, N, 7, 4, 0}};

    public static void main(String[] args) {
        dijkstra(0, Graph);
    }

    /**
     * Dijkstra最短路径。
     * 即图中"节点vs"到其它各个节点的最短路径。
     *
     * @param vs    起始节点
     * @param Graph 图
     */
    public static void dijkstra(int vs, int[][] Graph) {
        int NUM = Graph.length;
        //存放最小路径的数组
        int[] mindlist = new int[NUM];
        int[] prenode = new int[NUM];
        //是否已经发现最小路径
        boolean[] find = new boolean[NUM];
        int vnear = 0;
        for (int i = 0; i < NUM; i++) {
            find[i] = false;
            mindlist[i] = Graph[vs][i];
            prenode[i] = i;
        }
        find[vs] = true;
        for (int i = 1; i < NUM; i++) {
            int min = N;
            for (int j = 0; j < NUM; j++) {
                if (mindlist[j] < min && !find[j]) {
                    min = mindlist[j];
                    vnear = j;
                }
            }
            find[vnear] = true;
            for (int j = 0; j < NUM; j++) {
                if (!find[j] && (min + Graph[vnear][j]) < mindlist[j]) {
                    mindlist[j] = min + Graph[vnear][j];
                    prenode[j] = vnear;
                }
            }
        }
        for (int i = 0; i < NUM; i++) {
            System.out.println("v"+vs+"..."+prenode[i]+"->v"+i+",s="+mindlist[i]);
        }
    }
}