package fixedfield.ast;

import java.util.ArrayList;
import java.util.List;

public class DSetNode extends ASTNode {
	private List<RecordNode> records;
	
	public DSetNode() {
		setRecords(new ArrayList<RecordNode>());
	}

	public List<RecordNode> getRecords() {
		return records;
	}

	public void setRecords(List<RecordNode> records) {
		this.records = records;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(DataSet ");
		for (RecordNode rn : records) {
			sb.append(" " + rn.toString() + " ");
		}
		sb.append(") ");
		return sb.toString();
	}
	

}
