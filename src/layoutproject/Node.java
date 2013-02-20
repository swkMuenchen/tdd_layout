package layoutproject;


public class Node {

	private static final NodeContent ZERO_CONTENT = new NodeContent();
	
	private NodeContent content = ZERO_CONTENT;

	private Children children = new Children();

	private Box box;
	
	public void setContent(NodeContent content) {
		this.content = (NodeContent) content;
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public Box getBoundingBox() {
	    return box;
    }

	public void setBoundingBox(Box box) {
		this.box = box;
    }
}
