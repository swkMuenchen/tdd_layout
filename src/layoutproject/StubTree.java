package layoutproject;

public class StubTree implements Tree {
	private Position position = new Position(0, 0);
	private Size size;

	public StubTree(Size size) {
		this.size = size;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;

	}

}
