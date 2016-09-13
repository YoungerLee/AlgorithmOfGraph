package MST;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import mat_graph.MatGraph;
import mat_graph.Vertex;

public class Prim {
	private Prim() {

	}

	private static final int INFINT = 0x7fffffff;

	public static void mst_prim(MatGraph G, int r) {
		for (int i = 1; i <= G.n; i++) {
			G.v[i].key = INFINT;
			G.pi[i] = 0;
		}
		G.v[r].key = 0;
		Queue<Vertex> Q = new PriorityQueue<Vertex>(G.n, ver_key);
		for (int i = 1; i <= G.n; i++) {
			Q.add(G.v[i]);
		}
		while (!Q.isEmpty()) {
			int u = Q.poll().vertex;
			if (G.pi[u] > 0)
				System.out.println(G.pi[u] + " " + u + " " + G.Mat[G.pi[u]][u]);
			for (int v = 1; v <= G.n; v++) {
				if (G.Mat[u][v] > 0 && Q.contains(G.v[v])
						&& G.Mat[u][v] < G.v[v].key) {
					Q.remove(G.v[v]);
					G.pi[v] = u;
					G.v[v].key = G.Mat[u][v];
					Q.add(G.v[v]);
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
		MatGraph G = new MatGraph(9);
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
