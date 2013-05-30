package fixedfield.ast;

public class ANode extends FixNode {

	public ANode() {
		super();
	}
	
	@Override
	public String toString() {
		return "ANode [  id="  + this.getId() + " start=" + this.getStart() + " count=" + this.getCount() + " ]";
	}
}
