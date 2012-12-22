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

	/*
	 * (non-Javadoc)
	 * 
	 * @see layoutproject.Node#getSize()
	 */
	@Override
	public Size getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see layoutproject.Node#setContent(layoutproject.Content)
	 */
	@Override
	public void setContent(ContentNode content) {
		this.content = (TestContentNode) content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see layoutproject.Node#setSize(layoutproject.Size)
	 */
	@Override
	public void setSize(Size size) {
		this.size = size;

	}

	public void addChild(TestNode child) {
		children.add(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see layoutproject.Node#getContent()
	 */
	@Override
	public ContentNode getContent() {
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see layoutproject.Node#getChildren()
	 */
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
