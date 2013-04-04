package layoutproject;

public class StubTree implements Tree {
	private Size size;

	public StubTree(Box boundingBox) {
		super();
	}

	public StubTree(Size size) {
		this.size = size;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public Position getPosition() {
		return new Position(0, 0);
	}

}
