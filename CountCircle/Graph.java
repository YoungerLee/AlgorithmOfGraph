package deepSearch;

public class Graph {
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	private static final int INFINT = 32768;
	public int n;
	public int Mat[][];
	public int pi[];
	public Vertex v[];

	public Graph(int n) {
		this.n = n;
		Mat = new int[n + 1][];
		for (int i = 0; i <= n; i++)
			Mat[i] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				Mat[i][j] = INFINT;
			}
		}
		v = new Vertex[n + 1];
		for (int i = 0; i <= n; i++) {
			v[i] = new Vertex();
			v[i].vertex = i;
			v[i].key = INFINT;
			v[i].d = INFINT;
			v[i].pi = 0;
			v[i].color = WHITE;
		}
		pi = new int[n + 1];
		for (int i = 1; i <= n; i++)
			pi[i] = 0;
	}

	public Graph(Graph G) {
		this.n = G.n;
		Mat = new int[n + 1][];
		for (int i = 0; i <= n; i++)
			Mat[i] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				Mat[i][j] = G.Mat[i][j];
			}
		}
		v = new Vertex[n + 1];
		for (int i = 0; i <= n; i++) {
			v[i] = new Vertex();
			v[i].vertex = G.v[i].vertex;
			v[i].key = G.v[i].key;
			v[i].d = G.v[i].d;
			v[i].pi = G.v[i].pi;
			v[i].color = WHITE;
		}
		pi = new int[n + 1];
		for (int i = 1; i <= n; i++)
			pi[i] = G.pi[i];
	}

	public void addSingleEdge(int start, int end, int weight) {
		Mat[start][end] = weight;
	}

	public void addDoubleEdge(int start, int end, int weight) {
		addSingleEdge(start, end, weight);
		addSingleEdge(end, start, weight);
	}

	public void deleteSingleEdge(int start, int end) {
		Mat[start][end] = INFINT;
	}

	public void deleteDoubleEdge(int start, int end) {
		deleteSingleEdge(start, end);
		deleteSingleEdge(end, start);
	}
}
