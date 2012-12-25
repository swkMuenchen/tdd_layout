package layoutproject;

public class TestContentNode implements ContentNode {

	private Size size = Size.ZERO_SIZE;
	private Position position = Position.ZERO_POSITION;
	
	private boolean visible = true;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setSize(Size contentSize) {
		size = contentSize;
		
	}
	
	public Size getSize() {
		return size;
	}

}
