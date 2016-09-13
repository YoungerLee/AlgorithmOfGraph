package SPA;

import mat_graph.MatGraph;

public class TransitiveClosure {
	private static final int INFINT = 32767;

	private TransitiveClosure() {
	}

	/**
	 * 求一个关系的传递闭包算法
	 * 
	 * @param G
	 * @return 传递闭包的关系矩阵
	 */
	public static MatGraph transitiveClosure(MatGraph G) {
		int n = G.n;
		MatGraph T = new MatGraph(n);
		int i, j, k;
		// 把图转化为关系矩阵，若(i, j)∈R，T[i][j]=1;否则T[i][j]=0
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				if (i == j || G.Mat[i][j] != INFINT)
					T.Mat[i][j] = 1;
				else
					T.Mat[i][j] = 0;
			}
		}
		// 检验结点i到结点j之间是否存在以全部取自集合(1,2,...,k)为内部结点的路径
		for (k = 1; k <= n; k++) {
			for (i = 1; i <= n; i++) {
				for (j = 1; j <= n; j++)
					T.Mat[i][j] |= (T.Mat[i][k] & T.Mat[k][j]);
				// T.Mat[i][j] = T.Mat[i][j] | (T.Mat[i][k] & T.Mat[k][j]);
			}
		}
		return T;
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
		MatGraph G = new MatGraph(4);
		G.addSingleEdge(4, 1, 1);
		G.addSingleEdge(4, 3, 1);
		G.addSingleEdge(3, 2, 1);
		G.addSingleEdge(2, 3, 1);
		G.addSingleEdge(2, 4, 1);
		printGraph(G);
		MatGraph T = transitiveClosure(G);
		System.out.println("---------------------------");
		printGraph(T);
	}
}
