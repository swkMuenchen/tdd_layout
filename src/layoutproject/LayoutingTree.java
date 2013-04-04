package layoutproject;

public class LayoutingTree implements Tree {

	public final static int SPACE_AROUND = 50;
	private Node node;
	private Tree child;

	public void setRoot(Node node) {
		this.node = node;
	}

	public void addChild(Tree child) {
		this.child = child;
	}

	@Override
	public Size getSize() {
		return getNodeSize().extendHorizontally(
				getChildrenSize().getWidth() + Node.XGAP);
	}

	private Size getNodeSize() {
		return node.getSize();
	}

	private Size getChildrenSize() {
		if (child != null)
			return child.getSize();
		else
			return Size.ZERO_SIZE;
	}

	@Override
	public Position getPosition() {
		return null;
	}

}
