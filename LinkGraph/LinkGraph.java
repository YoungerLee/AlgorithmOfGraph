package link_graph;

import java.util.LinkedList;

public class LinkGraph {
	public int n;
	public Vertex[] vertex;
	private static final int INFINT = 32767;

	public LinkGraph(int num) {
		n = num;
		vertex = new Vertex[num + 1];
		for (int i = 0; i <= num; i++) {
			vertex[i] = new Vertex();
			vertex[i].mark = i;
			vertex[i].key = 0;
			vertex[i].pi = 0;
			vertex[i].d = INFINT;
			vertex[i].head = new LinkedList<Edge>();
		}
	}

	public Vertex getVertex(int index) {
		return vertex[index];
	}

	public Edge getEdge(int start, int end) {
		Vertex v = vertex[start];
		LinkedList<Edge> edges = v.head;
		for (int i = 0; edges != null && i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if (edge.start == start && edge.end == end)
				return edge;
		}
		return null;
	}

	public void addSingleEdge(int start, int end, int weight) {
		Edge e = new Edge(start, end, weight);
		if (vertex[start].head == null || vertex[start].head.isEmpty()) {
			vertex[start].head.add(e);
		} else {
			if (!vertex[start].head.contains(e)) {
				vertex[start].head.add(e);
			}
		}
	}

	public void addDoubleEdge(int start, int end, int weight) {
		addSingleEdge(start, end, weight);
		addSingleEdge(end, start, weight);
	}

	public void deleteSingleEdge(int start, int end) {
		LinkedList<Edge> edgeList = vertex[start].head;
		Edge edge = vertex[start].getEdge(start, end);
		if (edge != null) {
			edgeList.remove(edge);
		}
	}

	public void deleteDoubleEdge(int start, int end) {
		deleteSingleEdge(start, end);
		deleteSingleEdge(end, start);
	}
}
