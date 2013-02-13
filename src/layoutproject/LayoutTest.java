package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class LayoutTest {

	
	@Test
	public void noChildrenAndZeroSizeContentHasSizeDoubledSpaceAround() {
		Node node = new Node();
		Layout layout = new Layout(node);
		layout.doLayout();
		assertThat(node.getSize(), is(new Size(0,0).addSpaceAround(Layout.SPACE_AROUND)));
	}
	

	@Test
	public void oneChildAndZeroSizeContentHasChildSize() {
		Node node = new Node();
		Node child = new Node();
		child.setSize(new Size(1,1).addSpaceAround(Layout.SPACE_AROUND));
		node.addChild(child);
		Layout layout = new Layout(node);
		layout.doLayout();
		assertThat(node.getSize(), is(child.getSize()));
		assertTrue(child.getPosition().equals(new Position(0, 0)));
	}

}
