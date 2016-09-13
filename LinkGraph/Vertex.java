package link_graph;

import java.util.LinkedList;

/**
 * 表示顶点的类
 * 
 * @author Young
 * 
 */
public class Vertex {
	public int mark;
	public int pi;
	public int key;
	public int d;
	public LinkedList<Edge> head;

	public Vertex() {
	}

	public boolean isEdgeExist(int start, int end) {
		for (int i = 0; head != null && i < head.size(); i++) {
			Edge edge = head.get(i);
			if (edge.start == start && edge.end == end)
				return true;
		}
		return false;
	}

	public Edge getEdge(int start, int end) {
		for (int i = 0; head != null && i < head.size(); i++) {
			Edge edge = head.get(i);
			if (edge.start == start && edge.end == end)
				return edge;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mark;
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
		if (mark != other.mark)
			return false;
		return true;
	}
}
