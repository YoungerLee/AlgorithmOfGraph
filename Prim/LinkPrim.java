package MST;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import link_graph.Edge;
import link_graph.LinkGraph;
import link_graph.Vertex;

public class LinkPrim {
	private static final int INFINT = 0x7fffffff;

	private LinkPrim() {

	}

	/**
	 * 最小生成树Prim算法，图用邻接链表实现
	 * 
	 * @param G
	 *            邻接链表实现的图
	 * @param r
	 *            最小生成树的根结点
	 */
	public static void mst_prim(LinkGraph G, int r) {
		Vertex[] V = G.vertex;
		for (int i = 0; i < V.length; i++) {
			V[i].key = INFINT; // 将每个结点的key值设置为∞，key属性保存的是连接v和树中结点的所有边最小的权重
			V[i].pi = 0;
		}
		V[r].key = 0; // 根结点的key值设置为0，以便使该结点成为第一个被处理的结点
		Queue<Vertex> Q = new PriorityQueue<Vertex>(G.n, ver_key);// 基于key属性的最小优先队列Q
		for (int i = 0; i < V.length; i++) {
			Q.add(V[i]);
		}
		while (!Q.isEmpty()) {
			Vertex u = Q.poll();// 把权重最小的顶点弹出
			LinkedList<Edge> edges = u.head;
			if (u.pi > 0)
				System.out.println(u.pi + " " + u.mark + " "
						+ G.getEdge(u.pi, u.mark).weight);
			for (int i = 0; edges != null && i < edges.size(); i++) {// 遍历该顶点的所有邻接顶点
				Edge e = edges.get(i);
				Vertex v = G.getVertex(e.end);
				if (Q.contains(v) && e.weight < v.key) {// 如果该邻接边的权重小于该邻接顶点的键值，则降低键值
					// 降低键值操作
					Q.remove(v);
					v.pi = e.start;
					v.key = e.weight;
					Q.add(v);
				}
			}
		}
	}

	private static Comparator<Vertex> ver_key = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex a, Vertex b) {
			return (int) (a.key - b.key);
		}
	};

	public static void main(String[] args) {
		LinkGraph G = new LinkGraph(9);
		G.addDoubleEdge(1, 2, 4);
		G.addDoubleEdge(1, 8, 8);
		G.addDoubleEdge(2, 3, 8);
		G.addDoubleEdge(2, 8, 11);
		G.addDoubleEdge(3, 4, 7);
		G.addDoubleEdge(3, 9, 2);
		G.addDoubleEdge(3, 6, 4);
		G.addDoubleEdge(4, 5, 9);
		G.addDoubleEdge(4, 6, 14);
		G.addDoubleEdge(5, 6, 10);
		G.addDoubleEdge(6, 7, 2);
		G.addDoubleEdge(7, 9, 6);
		G.addDoubleEdge(7, 8, 1);
		G.addDoubleEdge(8, 9, 7);
		mst_prim(G, 1);
	}
}
