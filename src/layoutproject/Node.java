package layoutproject;

import java.util.ArrayList;
import java.util.Collection;

public class Node {

	private static final Content ZERO_CONTENT = new Content();
	private Content content = ZERO_CONTENT;
	private Size size;
	private Collection<Node> children = new ArrayList<Node>();

	public Size getSize() {
		return size;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void setSize(Size size) {
		this.size = size;
		
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public Content getContent() {
		return content;
	}

	public Collection<Node> getChildren() {
		return children;
	}
	
	

}
