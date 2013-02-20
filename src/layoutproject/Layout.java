package layoutproject;

public class Layout {
	
	public final static int SPACE_AROUND = 50;
	public final static int GAP = 3;
	
	private Node node;
	
	public Layout(Node node){
		this.node = node;
	}

	public void doLayout() {
		final Size size = new Size(0, 0);
		Position position = new Position(0, 0);
		final Box box = new Box(size, position ).addSpaceAround(Layout.SPACE_AROUND);
		node.setBoundingBox(box);
	}
	
}
