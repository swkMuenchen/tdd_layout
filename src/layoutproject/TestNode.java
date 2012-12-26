package layoutproject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestNode implements MapNode {

	private static final TestContentNode ZERO_CONTENT = new TestContentNode();
	
	private TestContentNode content = ZERO_CONTENT;
	private Size size = Size.ZERO_SIZE;
	private Position position = Position.ZERO_POSITION;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	private List<MapNode> children = new ArrayList<MapNode>();

	@Override
	public Size getSize() {
		return size;
	}
	
	@Override
	public void setContent(ContentNode content) {
		this.content = (TestContentNode) content;
	}

	@Override
	public void setSize(Size size) {
		this.size = size;
	}

	public void addChild(TestNode child) {
		children.add(child);
	}

	@Override
	public ContentNode getContent() {
		return content;
	}
	
	@Override
	public Collection<MapNode> getChildren() {
		return children;
	}

	public void setContentVisible(boolean visible) {
		content.setVisible(visible);
	}

	public MapNode getChild(int index) {
		return children.get(index);
	}

}
