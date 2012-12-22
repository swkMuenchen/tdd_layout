package layoutproject;

import java.util.Collection;

public class Layout {

	public void doLayout(Node node) {
		Content content = node.getContent();
		Collection<Node> children = node.getChildren();
		Size nodeSize;
		if(children.isEmpty())
		{
			Size contentSize = content.getSize();
			nodeSize = contentSize;
		}
		else{
			nodeSize = children.iterator().next().getSize();
		}
		node.setSize(nodeSize);
	}

}
