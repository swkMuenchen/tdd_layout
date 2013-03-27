package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class TreeTest {

	@Test
	public void noChildrenAndNode_BoxHasNodeSize() {
		LayoutingTree layoutingTree = new LayoutingTree();
		Node node = new Node(new Size(1,1));
		layoutingTree.setRoot(node);
		assertThat(layoutingTree.getSize(), is(new Size(1,1)));
	}
	
	@Test
	public void oneChildTreeWithHiddenRootNode_hasChildTreeSize() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(new Size(1,1));
		
		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);
		
		assertThat(parentTree.getSize(), is(new Size(1,1)));
	}

	@Test
	public void oneChildTreeWithHiddenRootNode_setsChildTreePositionToItsOrigin() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(new Size(1,1));
		
		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);
		
		assertThat(childTree.getPosition(), is(new Position(0,0)));
	}
}
