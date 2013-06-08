package layoutproject;

public class StubTree implements Tree {
	private Size size;
    private int x = 0;
    private int y = 0;

    public StubTree(int width, int height) {
        this.size = new Size(width, height);
    }

    private Size getSize() {
		return size;
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
