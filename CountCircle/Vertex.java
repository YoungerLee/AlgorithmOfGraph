package deepSearch;


public class Vertex {
	public int vertex;
	public int key;
	public int d; // 源结点到目标结点的距离
	public int pi;// 目标u结点的前驱结点
	public int color;// 结点的颜色标记

	public Vertex() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + vertex;
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
		Vertex other = (Vertex) obj;
		if (vertex != other.vertex)
			return false;
		return true;
	}
}
