package layoutproject;


public class LayoutingTree implements Tree{

	public final static int SPACE_AROUND = 50;
	private Node node;
	private Tree child;

	public void setRoot(Node node) {
		this.node = node;
		
	}

	public void addChild(Tree child) {
		this.child = child;
	}

	public Box getBoundingBox() {
		if(node.isHidden())
			return child.getBoundingBox();
		else{
			final Size size = node.getSize();
			Position position = new Position(0, 0);
			return new Box(size, position ).addSpaceAround(LayoutingTree.SPACE_AROUND);
		}
    }

}
