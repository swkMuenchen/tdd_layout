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
        return new Size(getWidth(), getHeight());
	}

    public int getHeight() {
        if (isLeaf())
            return getNodeHeight();
        else if (node.isHidden())
            return getChildrenHeight();
        else {
            return getNodeHeight() + Math.max(0, //
                -child.getPosition().getY());
        }
    }

    public int getWidth() {
        if (isLeaf()) {
            return getNodeWidth();
        }
        else if (node.isHidden())
            return getChildrenWidth();
        else {
            return getNodeWidth() + getChildrenWidth()
                + Node.X_GAP;
        }
    }

    private int getNodeWidth() {
        return getNodeSize().getWidth();
    }

    private int getChildrenWidth() {
        return getChildrenSize().getWidth();
    }

	public void layoutChildren() {
		if (!isLeaf() && !node.isHidden()) {
			int heightDiff = getNodeHeight()
				- getChildrenHeight();
			child.setPosition(new Position(1 + Node.X_GAP, heightDiff / 2
				- Node.Y_SHIFT));
		}
	}

    private int getChildrenHeight() {
        return getChildrenSize().getHeight();
    }

    private int getNodeHeight() {
        return getNodeSize().getHeight();
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
