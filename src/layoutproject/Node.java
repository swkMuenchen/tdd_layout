package layoutproject;

public class Node {

	public static final Node HIDDEN_ROOT = new Node(Size.ZERO_SIZE);
	final private Size size;
    private int yShift;

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

    public void setYShift(int yShift) {
        this.yShift = yShift;
    }

    public int getYShift() {
        return yShift;
    }
}
