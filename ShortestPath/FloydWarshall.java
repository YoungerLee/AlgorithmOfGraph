package SPA;

import mat_graph.MatGraph;

public class FloydWarshall {
	private static final int INFINT = 32767;

	private FloydWarshall() {
	}

	/**
	 * 计算图中任意一对结点的最短路径:使用传递闭包的性质，图G的传递闭包等价于所有结点对i和j之间是否存在路径
	 * 若存在通路，按照动态规划的思想计算，公式：d(i, j)=min{d(i, j), d(i, k)+d(k, j)}
	 * 
	 * @param W
	 * @return 最短路径矩阵，矩阵里的元素dij表示结点i到结点j的最短路径
	 */
	public static MatGraph floydWarshall(MatGraph W) {
		int n = W.n;
		MatGraph D = new MatGraph(W);
		int i, j, k;
		for (k = 1; k <= n; k++) {
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++) {
					// 检验结点i和j之间是否存在以 全部取自结点集合(1,2,...k)为内部结点的路径，
					// 存在则对最短路径d(i, j)进行动态规划
					if (D.Mat[i][k] != INFINT && D.Mat[k][j] != INFINT)
						D.Mat[i][j] = min(D.Mat[i][j], D.Mat[i][k]
								+ D.Mat[k][j]);
				}
			}
		}
		return D;
	}

	private static int min(int a, int b) {
		return a < b ? a : b;
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
		MatGraph D = floydWarshall(G);
		System.out.println("----------------------------");
		printGraph(D);
	}
}
