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
    public int getHeight() {
        if (isLeaf())
            return getNodeHeight();
        else if (node.isHidden())
            return getChildrenHeight();
        else {
            return getNodeHeight() + Math.max(0, //
                -child.getY());
        }
    }

    @Override
    public int getWidth() {
        if (isLeaf()) {
            return getNodeWidth();
        }
        else if (node.isHidden())
            return getChildWidth();
        else {
            return getNodeWidth() + getChildWidth() + Node.X_GAP;
        }
    }

    private int getNodeWidth() {
        return getNodeSize().getWidth();
    }

    private int getChildWidth() {
        return child != null ? child.getWidth() : 0;
    }

    public void layoutChildren() {
        layoutChildrenX();
        layoutChildrenY();
    }

    private void layoutChildrenY() {
        if (hasChild()) {
            child.setY(node.isHidden() ? 0 : computeY());
        }
    }

    private void layoutChildrenX() {
        if (hasChild() && !node.isHidden()) {
            child.setX(1 + Node.X_GAP);
        }
        else
            child.setX(0);
    }

    private boolean hasChild() {
        return !isLeaf();
    }

    private int computeY() {
        int heightDiff = getNodeHeight() - getChildrenHeight();
        return heightDiff / 2 - Node.Y_SHIFT;
    }

    private int getChildrenHeight() {
        return (child != null ? child.getHeight() : 0);
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

    @Override
    public void setX(int i) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setY(int i) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return 0;
    }

}
