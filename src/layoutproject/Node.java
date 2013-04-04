package layoutproject;

public class Node {

	public static final Node HIDDEN_ROOT = new Node(Size.ZERO_SIZE);
	public static final int XGAP = 5;
	final private Size size;

	public Node(Size size) {
		super();
		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public boolean isHidden() {
		return size.equals(Size.ZERO_SIZE);
	}
}
