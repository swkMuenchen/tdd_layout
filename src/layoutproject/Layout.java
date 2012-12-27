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
		Iterable<MapNode> children = node.getChildren();
		int nodeContentWidth = node.getContent().getSize().getWidth();
		int contentX = node.getContent().getPosition().getX();
		
		for (Node child : children) {
			child.setPosition(new Position(contentX + nodeContentWidth + child.getPosition().getX() , 0));
		}
		
	}

	private void setContentPosition() {
		Position contentPosition = new Position(SPACE_AROUND, SPACE_AROUND);
		ContentNode content = node.getContent();
		content.setPosition(contentPosition);
	}

	private void calculateRelativePositionsOfChildNodes() {
		Iterable<MapNode> children = node.getChildren();
		
		for (Node child : children) {
			child.setPosition(new Position(GAP + child.getPosition().getX() , 0));
		}
	
	}

	private void calculateNodeSize() {
		Node content = node.getContent();
		Iterable<MapNode> children = node.getChildren();
		Size nodeSize = Size.ZERO_SIZE;
		
		//Refactorings needed.
		if (children.iterator().hasNext()) {
			if (node.getContent().isVisible()) {
				nodeSize = nodeSize.add(GAP, 0);
			}
			nodeSize = nodeSize.add(children.iterator().next().getSize());
		} 
			
		Size contentSize = content.getSize().addSpaceAround(SPACE_AROUND);
		nodeSize = nodeSize.add(contentSize);
		
		node.setSize(nodeSize);
	}

}
