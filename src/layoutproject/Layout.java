package layoutproject;

public class Layout {
	
	public final static int SPACE_AROUND = 50;
	public final static int GAP = 3;
	
	private MapNode node;
	
	public Layout(MapNode node){
		this.node = node;
	}
	
	public void doLayout() {
		calculateRelativePositionsOfChildNodes();
		setContentPosition();
		calculateNodeSize();
		setAbsolutePositionsOfChildNodes();
	}
	
	private void setAbsolutePositionsOfChildNodes() {
		
	}

	private void setContentPosition() {
		Position contentPosition = new Position(SPACE_AROUND, SPACE_AROUND);
		ContentNode content = node.getContent();
		content.setPosition(contentPosition);
	}

	private void calculateRelativePositionsOfChildNodes() {
	
	}

	private void calculateNodeSize() {
		Node content = node.getContent();
		Iterable<MapNode> children = node.getChildren();
		Size nodeSize;
		
		if(children.iterator().hasNext()) {
			nodeSize = children.iterator().next().getSize();
		} else {
			Size contentSize = content.getSize().addSpaceAround(SPACE_AROUND);
			nodeSize = contentSize;
		}
		node.setSize(nodeSize);
	}

}
