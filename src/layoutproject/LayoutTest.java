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
		TestContentNode content = new TestContentNode();
		Size contentSize = new Size(1, 1);
		content.setSize(contentSize);
		
		MapNode node = new TestNode();
		node.setContent(content);
		
		Layout layout = new Layout(node);
		layout.doLayout();
		
		assertThat(node.getSize(), is(contentSize.addSpaceAround(Layout.SPACE_AROUND)));
		assertThat(node.getContent().getPosition(), is (new Position(Layout.SPACE_AROUND, Layout.SPACE_AROUND)));
	}
	
	@Test
	public void oneChildAndNoVisibleContentHasChildSize() {
		TestNode child = new TestNode();
		Size childSize = new Size(1,1);
		//Add space around instead of calling layout
		child.setSize(childSize.addSpaceAround(Layout.SPACE_AROUND));
		
		TestNode node = new TestNode();
		node.addChild(child);
		node.setContentVisible(false);

		Layout layout = new Layout(node);
		layout.doLayout();
		
		assertThat(node.getSize(), is(childSize.addSpaceAround(Layout.SPACE_AROUND)));
		//assertThat(node.getChild(0).getPosition(), is (new Position(0, 0)));
	}
	
	@Test
	public void oneChildAndHasVisibleContentHasChildSizePlusContentAndGap() {
		Size childSize = new Size(1, 1);
		Size contentSize = new Size(1, 1);
		
		TestNode child = new TestNode();
		//Size is set (mock)
		child.setSize(childSize.addSpaceAround(Layout.SPACE_AROUND));
		
		TestContentNode content = new TestContentNode();
		content.setSize(contentSize);
		
		TestNode node = new TestNode();
		node.addChild(child);
		node.setContent(content);
		node.setContentVisible(true);
		
		Layout layout = new Layout(node);
		layout.doLayout();

		int contentX = content.getPosition().getX();
		int contentWidth = content.getSize().getWidth();
		int childX = node.getChild(0).getPosition().getX();
		assertThat(childX , is (contentX + contentWidth + Layout.GAP));
		
		//Note we need to add on 100 to contentSize
		int scaledContentSizeX = node.getContent().getSize().getWidth() + Layout.SPACE_AROUND * 2;
		int scaledContentSizeY = node.getContent().getSize().getHeight() + Layout.SPACE_AROUND * 2;
		int sizeX = Layout.GAP + child.getSize().getWidth() + scaledContentSizeX;
		int sizeY = child.getSize().getHeight() + scaledContentSizeY;
		assertThat(node.getSize(), is (new Size (sizeX, sizeY)));
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
