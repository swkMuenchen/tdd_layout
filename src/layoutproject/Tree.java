package layoutproject;


public class Tree {

	private Children children = new Children();

	private Box box;
	
	public void setRoot(Node content) {
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	public Box getBoundingBox() {
	    return box;
    }

	public void setBoundingBox(Box box) {
		this.box = box;
    }
}
