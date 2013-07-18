package layoutproject;

import java.util.LinkedList;
import java.util.List;

public class LayoutingTree implements Tree {

    public final static int SPACE_AROUND = 50;
    private Node node;

    private List<Tree> children = new LinkedList<>();
    public static final int Y_GAP = 1;
    public static final int X_GAP = 5;
    public static final int ONE_CHILD_Y_SHIFT = 3;

    public LayoutingTree() {
        super();
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public Node getNode() {
        return node;
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    @Override
    public int getHeight() {
        if (isLeaf())
            return getNodeHeight();
        else if (node.isHidden())
            return getChildrenHeightWithoutShifts();
        else {
            Tree upperChild = getUpperChild();
            Tree lowerChild = getLowerChild();
            int lowerChildBottomCoordinate = lowerChild.getY() + lowerChild.getHeight();
            return Math.max(0, -upperChild.getY()) // 
                + Math.max(getNodeHeight(), lowerChildBottomCoordinate);
        }
    }

    private Tree getLowerChild() {
        return children.get(children.size() - 1);
    }

    @Override
    public int getWidth() {
        if (isLeaf()) {
            return getNodeWidth();
        }
        else if (node.isHidden())
            return getChildWidth();
        else {
            return getNodeWidth() + getChildWidth() + LayoutingTree.X_GAP;
        }
    }

    private int getNodeWidth() {
        return getNodeSize().getWidth();
    }

    private int getChildWidth() {
        return getUpperChild() != null ? getUpperChild().getWidth() : 0;
    }

    public void layoutChildren() {
        layoutChildrenX();
        layoutChildrenY();
    }

    private void layoutChildrenY() {
        int y = (getNodeHeight() - getChildrenHeightWithoutShifts()) / 2;
        if (!node.isHidden() && children.size() == 1)
            y -= LayoutingTree.ONE_CHILD_Y_SHIFT;
        int accumulatedChildShift = 0;
        for (final Tree child : children) {
            Node childNode = child.getNode();
            int childYShift = childNode.getYShift();
            child.setY(accumulatedChildShift + childYShift + y);
            accumulatedChildShift += childYShift;
            y += child.getHeight() + LayoutingTree.Y_GAP;
        }
    }

    private void layoutChildrenX() {
        if (hasChild() && !node.isHidden()) {
            getUpperChild().setX(1 + LayoutingTree.X_GAP);
        }
        else
            getUpperChild().setX(0);
    }

    private boolean hasChild() {
        return !isLeaf();
    }

    private int getChildrenHeightWithoutShifts() {
        int height = (children.size() - 1) * LayoutingTree.Y_GAP;
        for (final Tree child : children) {
            height += child.getHeight();
        }
        return height;
    }

    private int getNodeHeight() {
        return node.isHidden() ? 0 : getNodeSize().getHeight();
    }

    public boolean isLeaf() {
        return children.isEmpty();
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

    private Tree getUpperChild() {
        return children.get(0);
    }
}
