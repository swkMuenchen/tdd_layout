package layoutproject;

public class Layout {
	
	public final static int SPACE_AROUND = 50;
	public final static int GAP = 3;
	
	private Tree tree;
	
	public Layout(Tree tree){
		this.tree = tree;
	}

	public void doLayout() {
		final Size size = new Size(0, 0);
		Position position = new Position(0, 0);
		final Box box = new Box(size, position ).addSpaceAround(Layout.SPACE_AROUND);
		tree.setBoundingBox(box);
	}
	
}
