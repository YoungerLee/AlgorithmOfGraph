package link_graph;

/**
 * 表示边的类
 * 
 * @author Young
 * 
 */
public class Edge {
	public int start; // 起始顶点
	public int end; // 结束顶点
	public int weight; // 权重

	public Edge() {

	}

	public Edge(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
}
