package SPA;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import mat_graph.MatGraph;
import mat_graph.Vertex;

public class MatDijkstra {
	private static final int INFINT = 32767;

	/**
	 * 求每个结点到源结点的最短路径
	 * 
	 * @param G
	 *            输入图
	 * @param s
	 *            源结点编号
	 * @return 返回的结点集
	 */
	public static Set<Vertex> matDijkstra(MatGraph G, int s) {
		// Initialize_single_source(G, s);
		Vertex[] V = G.v;
		for (int i = 1; i <= G.n; i++) { // 每个结点的d属性保存了该结点到源结点的最短距离，初始化为无穷大
			V[i].d = INFINT;
			V[i].pi = 0;
		}
		V[s].d = 0; // 源结点的d属性设为0，以便第一个处理
		Set<Vertex> S = new HashSet<Vertex>();
		Queue<Vertex> Q = new PriorityQueue<Vertex>(G.n, ver_key);// 以d属性为关键字的最小优先队列
		for (int i = 1; i <= G.n; i++) {
			Q.add(G.v[i]);
		}
		while (!Q.isEmpty()) {
			Vertex U = Q.poll();// 每次把d属性值最小的结点弹出来，此处具有贪心性质
			S.add(U);
			int u = U.vertex;
			for (int v = 1; v <= G.n; v++) {// 对所有邻接边进行松弛操作(对d属性进行动态规划)
				// relax(G, u, v);
				if (V[v].d > V[u].d + G.Mat[u][v]) {
					Q.remove(V[v]);
					V[v].d = V[u].d + G.Mat[u][v];
					V[v].pi = u;
					Q.add(V[v]);
				}
			}
		}
		return S;
	}

	private static Comparator<Vertex> ver_key = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex a, Vertex b) {
			return (int) (a.d - b.d);
		}
	};

	/**
	 * 打印所有结点到源结点的最短路径及其距离
	 * 
	 * @param S
	 *            输入的结点集
	 */
	public static void printAllPath(Set<Vertex> S) {
		Iterator<Vertex> it = S.iterator();
		while (it.hasNext()) {
			Vertex vertex = (Vertex) it.next();
			printPath(S, vertex);
		}
	}

	/**
	 * 打印指定结点到源结点的最短路径及其距离，使用栈回溯法
	 * 
	 * @param S
	 *            结点集
	 * @param vertex
	 *            指定的结点
	 */
	private static void printPath(Set<Vertex> S, Vertex vertex) {
		int pi = vertex.pi;
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.add(vertex);
		Vertex v = vertex;
		while (pi > 0) {
			v = getPrevVertex(S, v);
			stack.push(v);
			pi = v.pi;
		}
		while (!stack.isEmpty()) {
			Vertex u = stack.pop();
			System.out.print(u.vertex);
			if (!stack.isEmpty()) {
				System.out.print("->");
			}
		}
		System.out.println(" 最短距离为:" + vertex.d);
	}

	/**
	 * 获取指定结点的前驱结点
	 * 
	 * @param S
	 * @param vertex
	 * @return 前驱结点，找不到返回null
	 */
	private static Vertex getPrevVertex(Set<Vertex> S, Vertex vertex) {
		Iterator<Vertex> it = S.iterator();
		while (it.hasNext()) {
			Vertex v = (Vertex) it.next();
			if (vertex.pi == v.vertex)
				return v;
		}
		return null;
	}

	public static void main(String[] args) {
		MatGraph G = new MatGraph(5);
		G.addSingleEdge(1, 2, 10);
		G.addSingleEdge(2, 3, 1);
		G.addSingleEdge(2, 5, 2);
		G.addSingleEdge(3, 4, 4);
		G.addSingleEdge(4, 3, 6);
		G.addSingleEdge(4, 1, 2);
		G.addSingleEdge(5, 4, 2);
		G.addSingleEdge(5, 3, 9);
		G.addSingleEdge(5, 2, 3);
		G.addSingleEdge(1, 5, 5);
		Set<Vertex> set = matDijkstra(G, 1);
		printAllPath(set);
	}
}
