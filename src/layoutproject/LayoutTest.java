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
		final Size size = new Size(0, 0);
		Position position = new Position(0, 0);
		final Box box = new Box(size, position ).addSpaceAround(Layout.SPACE_AROUND);
		
		assertThat(node.getBoundingBox(), is(box));
	}
	
	@Test
	public void boxWithAddedSpaceAround(){
		final Size size = new Size(0, 0);
		final Position position = new Position(0, 0);
		final int space = 1;
		final Box box = new Box(size, position ).addSpaceAround(space);
		final Size expectedSize = new Size(2*space, 2*space);
		final Position expectedPosition = new Position(-space, -space);
		assertThat(box, is(new Box(expectedSize, expectedPosition)));
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
