package deepSearch;

import java.util.ArrayList;
import java.util.List;

public class DeepFirstSearch {
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	private static final int INFINT = 32768;
	private static int flag[];

	private static int count = 0;

	/**
	 * 使用深度优先搜索方法对每个结点寻找回路
	 * 
	 * @param G
	 */

	private static int k;
	private static List<Integer> path = new ArrayList<Integer>();

	private static void DFS(Graph G) {

		int verNum = G.n;
		flag = new int[verNum + 1];

		for (int i = 0; i <= verNum; i++) {
			flag[i] = WHITE;
		}

		for (int v = 1; v <= verNum; v++) {
			path.clear();
			path.add(v);
			DFS_Visit(G, v, path); // 对每个结点调用深度搜索过程
		}

	}

	/**
	 * 对单个结点寻找回路
	 * 
	 * @param G
	 * @param s
	 *            源结点
	 * @param u
	 *            当前遍历结点
	 */
	private static void DFS_Visit(Graph G, int u, List<Integer> path) {
		int v;
		int verNum = G.n;
		flag[u] = GRAY;
		for (v = 1; v <= verNum; v++) {
			if (G.Mat[u][v] < INFINT) {
				if (v > path.get(0) && flag[v] == WHITE) {
					path.add(v);
					DFS_Visit(G, v, path);
				}
				if (path.get(0) == v) {
					k = path.size();
					for (int i = 0; i < path.size(); i++) {
						System.out.print(path.get(i));
						if (i != k - 1)
							System.out.print("->");
						if (i == k - 1) {
							System.out.print("->");
							System.out.print(path.get(0));
						}
					}
					System.out.print("\n");
					count++;
				}
			}
		}
		flag[path.get(path.size() - 1)] = WHITE;
		path.remove(path.size() - 1);
	}

	public static void main(String[] args) {
		Graph G = new Graph(6);

		G.addSingleEdge(1, 2, 1);
		G.addSingleEdge(2, 3, 1);
		G.addSingleEdge(2, 4, 1);
		G.addSingleEdge(2, 5, 1);
		G.addSingleEdge(3, 2, 1);
		G.addSingleEdge(3, 4, 1);
		G.addSingleEdge(4, 3, 1);
		G.addSingleEdge(4, 4, 1);
		G.addSingleEdge(4, 5, 1);
		G.addSingleEdge(5, 3, 1);
		G.addSingleEdge(5, 6, 1);

		DFS(G);
		System.out.println("回路数：" + count);
	}
}
