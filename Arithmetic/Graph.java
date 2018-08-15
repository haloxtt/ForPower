import java.util.LinkedList;

public class Graph {
	private int vertexSize;//????????
	private int [] vertexs;//????????
	private int[][]  matrix;
	private static final int MAX_WEIGHT = 1000;
	private boolean [] isVisited;
	public Graph(int vertextSize){
		this.vertexSize = vertextSize;
		matrix = new int[vertextSize][vertextSize];
		vertexs = new int[vertextSize];
		for(int i = 0;i<vertextSize;i++){
			vertexs[i] = i;
		}
		isVisited = new boolean[vertextSize];
	}
	
	
	/**
	 * ??????????????
	 * @return
	 */
	public int getOutDegree(int index){
		int degree = 0;
		for(int j = 0;j<matrix[index].length;j++){
			int weight = matrix[index][j];
			if(weight!=0&&weight!=MAX_WEIGHT){
				degree++;
			}
		}
		return degree;
	}
	
	
	
	/**
	 * ???
	 * @return
	 */
	
	/**
	 * ???????????????????
	 */
	public int getFirstNeighbor(int index){
		for(int j = 0;j<vertexSize;j++){
			if(matrix[index][j]>0&&matrix[index][j]<MAX_WEIGHT){
				return j;
			}
		}
		return -1;
	}

	public int getFirstNeighbor1(int index) {
		for (int i = 0; i < vertexSize; i++) {
			if (matrix[index][i] > 0 && matrix[index][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}
	public int getNextNeighbor(int v,int index){
		for(int j = index+1;j<vertexSize;j++){
			if(matrix[v][j]>0&&matrix[v][j]<MAX_WEIGHT){
				return j;
			}
		}
		return -1;
	}

	public int geiNextNeighbor1(int v,int index) {
		for (int i = index+1; i < vertexSize; i++) {
			if (matrix[v][i] > 0 && matrix[v][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * ???????????????
	 */
	private void depthFirstSearch(int i){
		isVisited[i] = true;
		int w = getFirstNeighbor(i);//
		while(w!=-1){
			if(!isVisited[w]){
				//????????????
				System.out.println("????????"+w+"????");
				depthFirstSearch(w);
			}
			w = getNextNeighbor(i, w);//??????????w??????
		}
	}

	private void depthFirstSearch1(int i) {
		isVisited[i] = true;
		int w = getFirstNeighbor(i);
		while (w != -1) {
			if (!isVisited[i]) {
				System.out.println("访问到了顶点"+i);
				depthFirstSearch(w);
			}
			w = getNextNeighbor(i,w);
		}
	}
	/**
	 * ??????????????????
	 */
	
	public void depthFirstSearch(){
		isVisited = new boolean[vertexSize];
		for(int i = 0;i<vertexSize;i++){
			if(!isVisited[i]){
				System.out.println("????????"+i+"????");
				depthFirstSearch(i);
			}
		}
		isVisited = new boolean[vertexSize];
	}
	public void depthFirstSearch1() {
		isVisited = new boolean[vertexSize];
		for (int i = 0; i < vertexSize; i++) {
			if (isVisited[i]) {
				System.out.println("已经访问到了顶点"+i);
				depthFirstSearch1(i);
			}
		}
		isVisited = new boolean[vertexSize];
	}
	
	public void broadFirstSearch(){
		isVisited = new boolean[vertexSize];
		for(int i =0;i<vertexSize;i++){
			if(!isVisited[i]){
				broadFirstSearch(i);
			}
		}
	}


	/**
	 * ????????????
	 * @param i
	 */
	private void broadFirstSearch(int i) {
		int u,w;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		System.out.println("???????"+i+"????");
		isVisited[i] = true;
		queue.add(i);//??????v0???????
		while(!queue.isEmpty()){
			u = (Integer)(queue.removeFirst()).intValue();
			w = getFirstNeighbor(u);
			while(w!=-1){
				if(!isVisited[w]){
					System.out.println("????????"+w+"????");
					isVisited[w] = true;
					queue.add(w);
				}
				w = getNextNeighbor(u, w);
			}
		}
	}

	private void broadFirstSearch1(int i) {
		int u,w;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		System.out.println("访问到了顶点"+i);
		isVisited[i] = true;
		queue.add(i);
		while (!queue.isEmpty()) {
			u = (queue.removeFirst()).intValue();
			w = getFirstNeighbor(u);
			while (w != -1) {
				if (!isVisited[w]) {
					System.out.println("访问到了顶点"+w);
					isVisited[w] =true;
					queue.add(w);
				}
				w = getNextNeighbor(u,w);
			}
		}
	}
/**
 * prim ???????
 */
	public void prim(){
		int [] lowcost = new int[vertexSize];//??????????????????,?0??????????????
		int [] adjvex = new int[vertexSize];//???????
		int min,minId,sum = 0;
		for(int i = 1;i<vertexSize;i++){
			lowcost[i] = matrix[0][i];
		}
		for(int i = 1;i<vertexSize;i++){
			min = MAX_WEIGHT;
			minId = 0;
			for(int j = 1;j<vertexSize;j++){
				if(lowcost[j]<min&&lowcost[j]>0){
					min = lowcost[j];
					minId = j;
				}
			}
			System.out.println("????"+adjvex[minId]+"????"+min);
			sum+=min;
			lowcost[minId] = 0;
			for(int j = 1;j<vertexSize;j++){
				if(lowcost[j]!=0&&matrix[minId][j]<lowcost[j]){
					lowcost[j] = matrix[minId][j];
					adjvex[j] = minId;
				}
			}
		}
		System.out.println("?????????????:"+sum);
	}
	
	/**
	 * ???????????????
	 */
	
	/**
	 * ?????????????????
	 * @return
	 */
	public int getWeight(int v1,int v2){
		int weight = matrix[v1][v2];
		return weight == 0?0:(weight == MAX_WEIGHT?-1:weight);
	}
	
	
	public int[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(int[] vertexs) {
		this.vertexs = vertexs;
	}

	public static void main(String [] args){
		Graph graph = new Graph(9);
		
		int [] a1 = new int[]{0,10,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a2 = new int[]{10,0,18,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,MAX_WEIGHT,12};
		int [] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,22,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,8};
		int [] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,22,0,20,MAX_WEIGHT,MAX_WEIGHT,16,21};
		int [] a5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,20,0,26,MAX_WEIGHT,7,MAX_WEIGHT};
		int [] a6 = new int[]{11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,26,0,17,MAX_WEIGHT,MAX_WEIGHT};
		int [] a7 = new int[]{MAX_WEIGHT,16,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,17,0,19,MAX_WEIGHT};
		int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,7,MAX_WEIGHT,19,0,MAX_WEIGHT};
		int [] a9 = new int[]{MAX_WEIGHT,12,8,21,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};
		
		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;
		
//		int degree = graph.getOutDegree(4);
//		System.out.println("vo?????:"+degree);
//		System.out.println("????"+graph.getWeight(2,3));
		graph.depthFirstSearch();
		graph.broadFirstSearch();
		//	graph.prim();
	}
}
