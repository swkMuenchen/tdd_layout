package layoutproject;

public class StubTree implements Tree{

	private Box boundingBox;
	
	public StubTree(Box boundingBox) {
		super();
		this.boundingBox = boundingBox;
	}

	@Override
	public Box getBoundingBox() {
		return boundingBox;
	}

}
