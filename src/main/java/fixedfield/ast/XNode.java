package fixedfield.ast;

public class XNode extends FixNode {
	public XNode() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "XNode [  id="  + this.getId() + " start=" + this.getStart() + " count=" + this.getCount() + " ]";
	}
}
