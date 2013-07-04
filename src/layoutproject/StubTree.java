package layoutproject;

public class StubTree implements Tree {
    private int x = 0;
    private int y = 0;
    private final Node node;

    @Override
    public Node getNode() {
        return node;
    }

    public StubTree(int width, int height) {
        node = new Node(new Size(width, height));
    }

    private Size getSize() {
        return node.getSize();
	}

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public int getHeight() {
        return getSize().getHeight();
    }

    @Override
    public int getWidth() {
        return getSize().getWidth();
    }

}
