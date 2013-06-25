package layoutproject;

import java.util.LinkedList;
import java.util.List;

public class LayoutingTree implements Tree {

    public final static int SPACE_AROUND = 50;
    private Node node;
    private List<Tree> children = new LinkedList<>();

    public LayoutingTree() {
        super();
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    @Override
    public int getHeight() {
        if (isLeaf())
            return getNodeHeight();
        else if (node.isHidden())
            return getChildrenHeight();
        else {
            return Math.max(getNodeHeight(), getChild().getY() + getChildrenHeight()) //
                + Math.max(0, -getChild().getY());
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
        return getChild() != null ? getChild().getWidth() : 0;
    }

    public void layoutChildren() {
        layoutChildrenX();
        layoutChildrenY();
    }

    private void layoutChildrenY() {
        int y = (getNodeHeight() - getChildrenHeight()) / 2;
        if (!node.isHidden() && children.size() == 1)
            y -= Node.Y_SHIFT;
        for (final Tree child : children) {
            child.setY(y);
            y += child.getHeight() + Node.Y_GAP;
        }
    }

    private void layoutChildrenX() {
        if (hasChild() && !node.isHidden()) {
            getChild().setX(1 + Node.X_GAP);
        }
        else
            getChild().setX(0);
    }

    private boolean hasChild() {
        return !isLeaf();
    }

    private int getChildrenHeight() {
        int height = (children.size() - 1) * Node.Y_GAP;
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

    private Tree getChild() {
        return children.get(0);
    }
}
