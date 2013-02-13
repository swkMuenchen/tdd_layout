package layoutproject;

public class Layout {
	
	public final static int SPACE_AROUND = 50;
	public final static int GAP = 3;
	
	private Node node;
	
	public Layout(Node node){
		this.node = node;
	}

	public void doLayout() {
		node.setSize(new Size(0,0).addSpaceAround(Layout.SPACE_AROUND));
		
	}
	
}
