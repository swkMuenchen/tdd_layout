package layoutproject;

public class StubTree implements Tree {
	private Size size;
    private int x = 0;
    private int y = 0;

	public StubTree(Size size) {
		this.size = size;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public Position getPosition() {
		return new Position(x, y);
	}

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

}
