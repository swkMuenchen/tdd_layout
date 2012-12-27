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
		Size childSize = new Size(1,1);
		
		//Add size around instead of calling layout to add this.
		TestNode child = new TestNode();
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
		
		//Add size around instead of calling layout to add this.
		TestNode child = new TestNode();
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
		
		int scaledContentSizeX = node.getContent().getSize().getWidth() + Layout.SPACE_AROUND * 2;
		int scaledContentSizeY = node.getContent().getSize().getHeight() + Layout.SPACE_AROUND * 2;
		int sizeX = Layout.GAP + child.getSize().getWidth() + scaledContentSizeX;
		int sizeY = child.getSize().getHeight() + scaledContentSizeY;
		assertThat(node.getSize(), is (new Size (sizeX, sizeY)));
	}
	
	@Test
	public void recursiveCallToLayoutWorksCorrectly() {
		Size childContentSize = new Size(5, 5);
		Size contentSize = new Size(1, 1);
		
		TestContentNode childContent = new TestContentNode();
		childContent.setSize(childContentSize);
		
		TestNode child = new TestNode();
		child.setContent(childContent);
		
		TestContentNode content = new TestContentNode();
		content.setSize(contentSize);
		
		TestNode node = new TestNode();
		node.addChild(child);
		node.setContent(content);
		node.setContentVisible(true);
		
		Layout childLayout = new Layout(child);
		childLayout.doLayout();
		
		Layout layout = new Layout(node);
		layout.doLayout();
		
		int scaledContentSizeX = node.getContent().getSize().getWidth() + Layout.SPACE_AROUND * 2;
		int scaledContentSizeY = node.getContent().getSize().getHeight() + Layout.SPACE_AROUND * 2;
		int sizeX = Layout.GAP + child.getSize().getWidth() + scaledContentSizeX;
		int sizeY = child.getSize().getHeight() + scaledContentSizeY;
		assertThat(node.getSize(), is (new Size (sizeX, sizeY)));
	}

	
}
