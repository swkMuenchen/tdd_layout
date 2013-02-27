package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class LayoutTest {

	
	@Test
	public void noChildrenAndZeroSizeContentHasSizeDoubledSpaceAround() {
		// Arrange
		Tree tree = new Tree();
		Node node = new Node();
		node.setSize(Size.EMPTY);
		tree.setRoot(node);
		Layout layout = new Layout(tree);
		
		//Act
		layout.doLayout();
		
		//Assert
		final Box box = Box.EMTPY_AT_ORIGIN.addSpaceAround(Layout.SPACE_AROUND);
		
		assertThat(tree.getBoundingBox(), is(box));
	}
	
	
	@Test
	public void oneChildAndZeroSizeContent_hasChildSize() {
		Tree parentTree = new Tree();
		Tree childTree = new Tree();
		childTree.setSize(
			new Size(1,1)
		);
		
		parentTree.addChild(childTree);
		Layout layout = new Layout(parentTree);
		
		layout.doLayout();
		
		assertThat(parentTree.getSize(), is(childTree.getSize()));
		assertTrue(childTree.getPosition().equals(new Position(0, 0)));
	}

}
