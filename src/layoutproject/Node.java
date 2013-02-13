package layoutproject;


public class Node {

	private static final NodeContent ZERO_CONTENT = new NodeContent();
	
	private NodeContent content = ZERO_CONTENT;
	private Size size = Size.ZERO_SIZE;
	private Position position = Position.ZERO_POSITION;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	private Children children = new Children();

	public Size getSize() {
		return size;
	}
	
	
	public void setContent(NodeContent content) {
		this.content = (NodeContent) content;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public Size getBoundingBox() {
	    // TODO Auto-generated method stub
	    return null;
    }
}
