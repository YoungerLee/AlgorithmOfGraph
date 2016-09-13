package SPA;

import mat_graph.MatGraph;

public class Johnson {
	private Johnson() {

	}

	private static final int INFINT = 32767;
	private static final int s = 0;

	/**
	 * Johnson算法求图G中任意结点对的最短路径
	 * 
	 * @param G
	 * @return 最短路径矩阵，其中的元素d(i, j)=δ(i, j)
	 */
	public static int[][] johnson(MatGraph G) {
		int n = G.n;
		int[][] D = new int[n + 1][n + 1];
		// 初始化最短路径矩阵
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++)
				D[i][j] = INFINT;
		// 构造另一个图G1，通过重新赋值生成非负值权重且不改变原来的最短路径
		MatGraph G1 = new MatGraph(G);
		G1.v[0].vertex = s;// 新增加的结点s
		for (int i = 1; i <= G.n; i++) {// 结点s与图G中的每一个结点连接权重为0的边
			G1.addSingleEdge(0, i, 0);
		}
		if (MatBellmanFord.matBellmanFord(G1, s) == false) {
			// 说明构造出来的图G1存在负权环，最短路径不存在
			System.out
					.println("the input graph contains a negative-weight cycle");
		} else {
			// h[] 保存图G1中除s结点外所有结点到结点s的最短路径
			int[] h = new int[n + 1];
			for (int i = 1; i <= n; i++)
				h[i] = G1.v[i].d;
			for (int u = 1; u <= n; u++) {
				for (int v = 1; v <= n; v++) {
					// 重新对G赋予新的权重，消除负权重边
					if (G.Mat[u][v] != INFINT) {
						G.Mat[u][v] += (h[u] - h[v]);
					}
				}
			}
			for (int u = 1; u <= n; u++) {
				// 此时可以使用Dijkstra算法对图G中的每一个结点到其他结点求最短路径，结果保存到矩阵D中
				MatDijkstra.matDijkstra(G, u);
				for (int v = 1; v <= n; v++) {
					D[u][v] = G.v[v].d + h[v] - h[u];
				}
			}
		}
		return D;
	}

	public static void printGraph(MatGraph G) {
		int n = G.n;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (G.Mat[i][j] == INFINT) {
					System.out.print('∞');
					System.out.print(' ');
				} else {
					System.out.print(G.Mat[i][j]);
					System.out.print(' ');
				}
				if (j == n)
					System.out.print('\n');
			}
		}
	}

	public static void main(String[] args) {
		MatGraph G = new MatGraph(5);
		G.addSingleEdge(1, 2, 3);
		G.addSingleEdge(1, 3, 8);
		G.addSingleEdge(1, 5, -4);
		G.addSingleEdge(2, 5, 7);
		G.addSingleEdge(2, 4, 1);
		G.addSingleEdge(3, 2, 4);
		G.addSingleEdge(4, 3, -5);
		G.addSingleEdge(4, 1, 2);
		G.addSingleEdge(5, 4, 6);
		printGraph(G);
		System.out.println("--------------------------");
		int[][] D = johnson(G);
		int n = G.n;
		for (int i = 1; i <= G.n; i++) {
			for (int j = 1; j <= n; j++) {
				if (D[i][j] == INFINT) {
					System.out.print('∞');
					System.out.print(' ');
				} else {
					System.out.print(D[i][j]);
					System.out.print(' ');
				}
				if (j == n)
					System.out.print('\n');
			}
		}
	}
}
