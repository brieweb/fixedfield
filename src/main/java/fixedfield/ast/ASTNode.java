package fixedfield.ast;

public class ASTNode {
	
	String type;
	
	public ASTNode(String myType) {
		type = myType;
	}
	
	public ASTNode() {
		type = "nothing";
	}


	@Override
	public String toString() {
		
		return " " + type + " ";
	}

}