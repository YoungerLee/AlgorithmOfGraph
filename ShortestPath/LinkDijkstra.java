package SPA;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import link_graph.Edge;
import link_graph.LinkGraph;
import link_graph.Vertex;

public class LinkDijkstra {
	private static final int INFINT = 32767;

	public static Set<Vertex> linkDijkstra(LinkGraph G, int s) {
		Vertex[] V = G.vertex;
		for (int i = 1; i <= G.n; i++) {// 每个结点的d属性保存了该结点到源结点的最短距离，初始化为无穷大
			V[i].d = INFINT;
			V[i].pi = 0;
		}
		V[s].d = 0;// 源结点的d属性设为0，以便第一个处理
		Set<Vertex> S = new HashSet<Vertex>();
		Queue<Vertex> Q = new PriorityQueue<Vertex>(G.n, ver_key);// 以d属性为关键字的最小优先队列
		for (int i = 1; i <= G.n; i++) {
			Q.add(V[i]);
		}
		while (!Q.isEmpty()) {
			Vertex u = Q.poll();// 每次把d属性值最小的结点弹出来，此处具有贪心性质
			S.add(u);
			LinkedList<Edge> edges = u.head;
			for (int i = 0; edges != null && i < edges.size(); i++) {// 对所有邻接边进行松弛操作(对d属性进行动态规划)
				Edge e = edges.get(i);
				Vertex v = G.getVertex(e.end);
				if (v.d > u.d + e.weight) {
					Q.remove(v);
					v.d = u.d + e.weight;
					v.pi = u.mark;
					Q.add(v);
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
			System.out.print(u.mark);
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
			if (vertex.pi == v.mark)
				return v;
		}
		return null;
	}

	public static void main(String[] args) {
		LinkGraph G = new LinkGraph(5);
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
		Set<Vertex> set = linkDijkstra(G, 1);
		printAllPath(set);
	}
}
