package stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSource {
	private static Map<String, Edge> edges = new HashMap<String, Edge>();
	static {
		Edge edge1 = new Edge("S", "V1", 0, 16, 16);
		Edge edge11 = new Edge("V1", "S", 0, 0, 0);
		Edge edge2 = new Edge("V1", "V2", 0, 0, 0);
		Edge edge3 = new Edge("V2", "V1", 0, 4, 4);
		Edge edge8 = new Edge("V2", "V4", 0, 14, 14);
		Edge edge12 = new Edge("V4", "V2", 0, 0, 0);
		Edge edge4 = new Edge("S", "V2", 0, 13, 13);
		Edge edge13 = new Edge("V2", "S", 0, 0, 0);
		Edge edge5 = new Edge("V1", "V3", 0, 12, 12);
		Edge edge14 = new Edge("V3", "V1", 0, 0, 0);
		Edge edge6 = new Edge("V3", "V2", 0, 9, 9);
		Edge edge15 = new Edge("V2", "V3", 0, 0, 0);
		Edge edge7 = new Edge("V3", "T", 0, 20, 20);
		Edge edge16 = new Edge("T", "V3", 0, 0, 0);
		Edge edge9 = new Edge("V4", "V3", 0, 7, 7);
		Edge edge17 = new Edge("V3", "V4", 0, 0, 0);
		Edge edge10 = new Edge("V4", "T", 0, 4, 4);
		Edge edge18 = new Edge("T", "V4", 0, 0, 0);
		edges.put("S->V1", edge1);
		edges.put("V1->S", edge11);
		edges.put("V1->V2", edge2);
		edges.put("V2->V1", edge3);
		edges.put("V2->V4", edge8);
		edges.put("V4->V2", edge12);
		edges.put("S->V2", edge4);
		edges.put("V2->S", edge13);
		edges.put("V1->V3", edge5);
		edges.put("V3->V1", edge14);
		edges.put("V3->V2", edge6);
		edges.put("V2->V3", edge15);
		edges.put("V3->T", edge7);
		edges.put("T->V3", edge16);
		edges.put("V4->V3", edge9);
		edges.put("V3->V4", edge17);
		edges.put("V4->T", edge10);
		edges.put("T->V4", edge18);
	}

	/**
	 * 获取指定起点可到达的边的集合
	 */
	public static List<Edge> nextEdge(String start) {
		List<Edge> res = new ArrayList<Edge>();
		for (String edge : edges.keySet()) {
			if (edge.startsWith(start)) {
				res.add(edges.get(edge));
			}
		}
		return res;
	}

	/**
	 * 由路径获取边
	 */
	public static Edge getEdge(String path) {
		return edges.get(path);
	}

	/**
	 * 获取所有的边
	 */
	public static Collection<Edge> getAllEdge() {
		return edges.values();
	}
}
