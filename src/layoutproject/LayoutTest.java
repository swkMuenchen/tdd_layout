package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class LayoutTest {

	
	@Test
	public void noChildrenAndZeroSizeContentHasSizeDoubledSpaceAround() {
		// Arrange
		Node node = new Node();
		NodeContent content = new NodeContent();
		content.setSize(new Size(0, 0));
		node.setContent(content );
		Layout layout = new Layout(node);
		
		//Act
		layout.doLayout();
		
		//Assert
		assertThat(node.getBoundingBox(), is(new Size(0,0).addSpaceAround(Layout.SPACE_AROUND)));
	}
	
	
//	@Test
//	public void oneChildAndZeroSizeContent_hasChildSize() {
//		Node parentNode = new Node();
//		Node childNode = new Node();
//		childNode.setSize(
//			new Size(1,1)
//		);
//		
//		parentNode.addChild(childNode);
//		Layout layout = new Layout(parentNode);
//		
//		layout.doLayout();
//		
//		assertThat(parentNode.getSize(), is(childNode.getSize()));
//		assertTrue(childNode.getPosition().equals(new Position(0, 0)));
//	}

}
