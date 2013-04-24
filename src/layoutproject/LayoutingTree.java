package layoutproject;

public class LayoutingTree implements Tree {

	public final static int SPACE_AROUND = 50;
	private Node node;
	private Tree child;

	public LayoutingTree() {
		super();
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void addChild(Tree child) {
		this.child = child;
	}

	@Override
	public Size getSize() {
		if (isLeaf())
			return getNodeSize();
		else if (node.isHidden())
			return getChildrenSize();
		else {
			Size size = getNodeSize().extendHorizontally(
				getChildrenSize().getWidth() + Node.X_GAP);
			size = size.extendVertically(Math.max(0, //
				-child.getPosition().getY()));
			return size;
		}
	}

	public void layoutChildren() {
		if (!isLeaf() && !node.isHidden()) {
			int heightDiff = getNodeSize().getHeight()
				- getChildrenSize().getHeight();
			child.setPosition(new Position(1 + Node.X_GAP, heightDiff / 2
				- Node.Y_SHIFT));
		}
	}

	public boolean isLeaf() {
		return child == null;
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

	@Override
	public void setPosition(Position position) {

	}

}
