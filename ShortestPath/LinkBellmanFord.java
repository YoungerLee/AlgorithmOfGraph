package SPA;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import link_graph.Edge;
import link_graph.LinkGraph;
import link_graph.Vertex;

public class LinkBellmanFord {
	private LinkBellmanFord() {

	}

	private static final int INFINT = 32767;

	/**
	 * 一般情况下的单源最短路径算法
	 * 
	 * @param G
	 * @param s
	 * @return true:输入图不包含可以从源结点到达的权重为负值的环路 ;false:输入图包含可以从源结点到达的权重为负值的环路
	 */
	public static boolean linkBellmanFord(LinkGraph G, int s) {
		// Initialize_single_source(G, s);
		Vertex[] V = G.vertex;
		for (int i = 1; i <= G.n; i++) {// 每个结点的d属性保存了该结点到源结点的最短距离，初始化为无穷大
			V[i].d = INFINT;
			V[i].pi = 0;
		}
		V[s].d = 0;// 源结点的d属性设为0，以便第一个处理
		/* 对所有边进行n-1次松弛操作，n为结点数 */
		for (int cnt = 1; cnt <= G.n - 1; cnt++) {
			for (int i = 1; i <= G.n; i++) {
				Vertex u = V[i];
				LinkedList<Edge> edges = u.head;
				Iterator<Edge> it = edges.iterator();
				while (it.hasNext()) {
					Edge edge = (Edge) it.next();
					Vertex v = G.getVertex(edge.end);
					if (v.d > u.d + edge.weight) {
						v.d = u.d + edge.weight;
						v.pi = u.mark;
					}
				}
			}
		}
		/* 检查是否有具有负权环，有则返回false */
		for (int i = 1; i <= G.n; i++) {
			Vertex u = V[i];
			LinkedList<Edge> edges = u.head;
			Iterator<Edge> it = edges.iterator();
			while (it.hasNext()) {
				Edge edge = (Edge) it.next();
				Vertex v = G.getVertex(edge.end);
				if (v.d > u.d + edge.weight) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 打印图中所有结点到源结点的最短路径
	 * 
	 * @param G
	 */
	public static void printAllPath(LinkGraph G) {
		Vertex[] V = G.vertex;
		for (int i = 1; i <= G.n; i++) {
			printPath(G, V[i]);
		}
	}

	/**
	 * 打印每个结点到源结点的最短路径及其路径值
	 * 
	 * @param G
	 */
	private static void printPath(LinkGraph G, Vertex vertex) {
		int pi = vertex.pi;
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.add(vertex);
		Vertex v = vertex;
		while (pi > 0) {
			v = getPrevVertex(G, v);
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
	 * @param G
	 * @param vertex
	 * @return 该结点的前驱结点
	 */
	private static Vertex getPrevVertex(LinkGraph G, Vertex vertex) {
		Vertex[] V = G.vertex;
		for (int i = 1; i <= G.n; i++) {
			if (V[i].mark == vertex.pi)
				return V[i];
		}
		return null;
	}

	public static void main(String[] args) {
		LinkGraph G = new LinkGraph(5);
		G.addSingleEdge(1, 2, 6);
		G.addSingleEdge(2, 3, 5);
		G.addSingleEdge(2, 4, -4);
		G.addSingleEdge(2, 5, 8);
		G.addSingleEdge(3, 2, -2);
		G.addSingleEdge(4, 3, 7);
		G.addSingleEdge(4, 1, 2);
		G.addSingleEdge(5, 4, 9);
		G.addSingleEdge(5, 3, -3);
		G.addSingleEdge(1, 5, 7);

		System.out.println(linkBellmanFord(G, 1));
		printAllPath(G);
	}
}
