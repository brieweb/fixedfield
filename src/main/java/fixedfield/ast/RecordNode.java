package fixedfield.ast;

import java.util.ArrayList;
import java.util.List;

public class RecordNode extends ASTNode {
	private String id;
	private int row;
	private List<FixNode> fields;
	
	public RecordNode() {
		setFields(new ArrayList<FixNode>());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<FixNode> getFields() {
		return fields;
	}

	public void setFields(List<FixNode> fields) {
		this.fields = fields;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(RecordNode " + id + "( ");
		for (FixNode a : fields) {
			sb.append(" " + a.toString() + ", ");
		}
		sb.append(")  row=" + row + ")");
		return sb.toString();
	}



}
