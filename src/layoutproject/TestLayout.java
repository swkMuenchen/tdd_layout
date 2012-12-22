package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import layoutproject.Node;

import org.junit.Test;


public class TestLayout {

	@Test
	public void noChildrenAndNoContentHasZeroSize() {
		Node node = new Node();
		Layout layout = new Layout();
		layout.doLayout(node);
		assertThat(node.getSize(), is(new Size(0,0)));
	}

	@Test
	public void noChildrenAndSomeContentHasContentsSize() {
		Node node = new Node();
		Content content = new Content();
		node.setContent(content);
		Size contentSize = new Size(1, 1);
		content.setSize(contentSize);
		Layout layout = new Layout();
		layout.doLayout(node);
		assertThat(node.getSize(), is(contentSize));
	}
	
	@Test
	public void oneChildAndNoContentHasChildSize() {
		Node parent = new Node();
		Node child = new Node();
		Size childSize = new Size(1,1);
		child.setSize(childSize);
		parent.addChild(child);
		Layout layout = new Layout();
		layout.doLayout(parent);
		assertThat(parent.getSize(), is(childSize));
	}
	
}
