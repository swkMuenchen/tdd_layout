package layoutproject;

public class StubTree implements Tree{

	private Box boundingBox;
	
	public StubTree(Box boundingBox) {
		super();
		this.boundingBox = boundingBox;
	}

	public StubTree(Size size) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Box getBoundingBox() {
		return boundingBox;
	}
	
	public Size getSize(){
		return new Size(1, 1);
	}

	public Position getPosition() {
		return new Position(0, 0);
	}

}
