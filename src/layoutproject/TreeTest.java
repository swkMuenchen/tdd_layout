package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class TreeTest {

	@Test
	public void noChildrenAndNode_BoxHasSizeDoubledSpaceAroundPlusNodeSize() {
		Tree tree = treeWithoutChildrenWithNodeSize(new Size(1,1));

		Box boundingBox = tree.getBoundingBox();
		
		assertThat(boundingBox, is(boxWithSize(1,1).addSpaceAround(Tree.SPACE_AROUND)));
	}
	

	private Box boxWithSize(int width, int height) {
		return new Box(new Size(width, height), Position.ORIGIN);
	}

	private Tree treeWithoutChildrenWithNodeSize(Size size) {
		Tree tree = new Tree();
		Node node = new Node();
		node.setSize(size);
		tree.setRoot(node);
		return tree;
	}
	
	
//	@Test
//	public void oneChildTreeWithHiddenRootNode_hasChildTreeSize() {
//		Tree parentTree = new Tree();
//		Tree childTree = new Tree();
//		childTree.setSize(
//			new Size(1,1)
//		);
//		
//		parentTree.setRoot(Node.HIDDEN_ROOT);
//		parentTree.addChild(childTree);
//		Layout layout = new Layout(parentTree);
//		
//		layout.doLayout();
//		
//		assertThat(parentTree.getSize(), is(childTree.getSize()));
//		assertTrue(childTree.getPosition().equals(new Position(0, 0)));
//	}

}
