package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import layoutproject.TestNode;

import org.junit.Test;


public class LayoutTest {

	@Test
	public void noChildrenAndZeroSizeContentHasSizeDoubledSpaceAround() {
		MapNode node = new TestNode();
		Layout layout = new Layout(node);
		layout.doLayout();
		assertThat(node.getSize(), is(new Size(0,0).addSpaceAround(Layout.SPACE_AROUND)));
	}

	@Test
	public void noChildrenAndSomeContentHasContentsSize() {
		MapNode node = new TestNode();
		TestContentNode content = new TestContentNode();
		node.setContent(content);
		Size contentSize = new Size(1, 1);
		content.setSize(contentSize);
		Layout layout = new Layout(node);
		layout.doLayout();
		assertThat(node.getSize(), is(contentSize.addSpaceAround(Layout.SPACE_AROUND)));
		assertThat(node.getContent().getPosition(), is (new Position(Layout.SPACE_AROUND, Layout.SPACE_AROUND)));
	}
	
	@Test
	public void oneChildAndNoVisibleContentHasChildSize() {
		TestNode node = new TestNode();
		node.setContentVisible(false);
		TestNode child = new TestNode();
		Size childSize = new Size(1,1);
		child.setSize(childSize);
		node.addChild(child);
		Layout layout = new Layout(node);
		layout.doLayout();
		assertThat(node.getSize(), is(childSize));
		assertThat(node.getChild(0).getPosition(), is (new Position(0, 0)));
	}
	
	@Test
	public void oneChildAndHasVisibleContentHasChildSizePlusContentAndGap() {
		TestNode node = new TestNode();
		node.setContentVisible(true);
		TestNode child = new TestNode();
		Size childSize = new Size(1,1);
		child.setSize(childSize);
		TestContentNode content = new TestContentNode();
		node.setContent(content);
		Size contentSize = new Size(1, 1);
		content.setSize(contentSize);
		node.addChild(child);
		Layout layout = new Layout(node);
		layout.doLayout();

		int contentX = content.getPosition().getX();
		int contentWidth = content.getSize().getWidth();
		int childX = node.getChild(0).getPosition().getX();
		assertThat(childX - contentX - contentWidth, is (Layout.GAP));
		
		
		
		assertThat(node.getContent().getPosition(), is (new Position(Layout.SPACE_AROUND, Layout.SPACE_AROUND)));
		assertThat(node.getSize(), is(childSize));
	}
//	
//	@Test
//	public void theOnlyChildBecomesLocation() {
//		TestNode child = new TestNode();
//		TestNode node = child;
//		node.addChild(child);
//		Layout layout = new Layout();
//		layout.doLayout(node);
//		assertThat(child.getLocation(), is(new Location(0,0)));
//	}
//	
//	
	
}
