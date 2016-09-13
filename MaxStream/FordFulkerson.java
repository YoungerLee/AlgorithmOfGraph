package stream;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FordFulkerson {
	private static Set<Edge> path = new HashSet<Edge>();

	public static void getMaxStream(String start, String end) {
		while (hasAugmentPath(start, end)) {
			int minRemain = getMinRemain(path);
			for (Edge edge : path) {
				Edge reverseEdge = DataSource.getEdge(edge.getReversePath());// 获取返回路径
				// 计算当前流值
				if (edge.getRemain() > edge.getCapacity()) {// 残存值大于最大容量,表明该路径是残存网路中的路径
					if (edge.getRemain() >= reverseEdge.getCapacity()) {// 残存量大于返回路径的最大容量
						reverseEdge.setValue(reverseEdge.getCapacity());// 返回路径设置成最大容量
						edge.setValue(edge.getRemain()
								- reverseEdge.getCapacity());
					} else {// 残存量小于返回路径的最大容量
						reverseEdge.setValue(edge.getRemain());
						edge.setValue(0);
					}
				} else {
					edge.setValue(edge.getValue() + minRemain);
				}
				// 计算残存值
				edge.setRemain(edge.getRemain() - minRemain);
				reverseEdge.setRemain(reverseEdge.getRemain() + minRemain);
			}
			path.clear();
		}
	}

	public static void printStreamInfo() {
		List<Edge> edges = DataSource.nextEdge("S");
		int maxStream = 0;
		for (Edge edge : edges) {
			if (edge.getCapacity() != 0) {
				maxStream += edge.getValue();
			}
		}
		System.out.println("最大流为：" + maxStream);
		System.out.println("流图信息如下：");
		for (Edge edge : DataSource.getAllEdge()) {
			if (edge.getCapacity() != 0) {
				System.out.println(edge.getStart() + "->" + edge.getEnd()
						+ ":\t" + edge.getValue() + "/" + edge.getCapacity());
			}
		}
	}

	private static boolean hasAugmentPath(String start, String end) {
		for (Edge edge : DataSource.nextEdge(start)) {
			if (path.contains(edge) || edge.getRemain() == 0) {// 出现回路||没有残余流量
				continue;
			}
			path.add(edge);
			if (edge.getEnd().equals(end)) {
				return true;// 找到增广路径
			} else {// 深度递归
				if (hasAugmentPath(edge.getEnd(), end)) {
					return true;
				} else {
					path.remove(edge);
					continue;
				}
			}
		}
		return false;// 遍历结束,没有找到增广路径
	}

	/**
	 * 获取最小残余量
	 * 
	 * @param edges
	 * @return
	 */
	private static int getMinRemain(Set<Edge> edges) {
		int res = Integer.MAX_VALUE;
		for (Edge edge : edges) {
			if (edge.getRemain() < res) {
				res = edge.getRemain();
			}
		}
		return res;
	}
}
