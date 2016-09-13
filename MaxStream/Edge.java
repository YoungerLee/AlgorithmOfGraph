package stream;

/**
 * 覆盖了equals和hashCode方法
 * Edge("start","end",0,0,0)和Edge("end","start",0,0,0)两个对象是相等的
 */
public class Edge {
	private String start;// 起始点
	private String end;// 终结点
	private int value;// 当前值
	private int remain;// 残留值
	private int capacity;// 最大流量,残留值有可能大于最大值,因为路径有往返的情况

	public Edge(String start, String end, int value, int remain, int capacity) {
		this.start = start;
		this.end = end;
		this.value = value;
		this.remain = remain;
		this.capacity = capacity;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getReversePath() {
		return end + "->" + start;
	}

	@Override
	public String toString() {
		return start + "->" + end;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += ((end == null) ? 0 : end.hashCode());
		result += ((start == null) ? 0 : start.hashCode());
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
		if (getStart().endsWith(other.getStart())
				&& getEnd().equals(other.getEnd())) {
			return true;
		}
		if (getStart().endsWith(other.getEnd())
				&& getEnd().equals(other.getStart())) {
			return true;
		}
		return false;
	}
}
