package fixedfield.ast;

public class FixNode extends ASTNode {
	private int count;
	private int start;
	private String id;
	
	public FixNode() {
		
	
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "FixNode [count=" + count + ", start=" + start + ", id=" + id
				+ "]";
	}
	
	
}
