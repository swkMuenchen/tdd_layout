package layoutproject;


public class Tree {

	private Children children = new Children();
	public final static int SPACE_AROUND = 50;
	private Node node;

	public void setRoot(Node node) {
		this.node = node;
		
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	public Box getBoundingBox() {
		final Size size = node.getSize();
		Position position = new Position(0, 0);
		return new Box(size, position ).addSpaceAround(Tree.SPACE_AROUND);
    }

}
