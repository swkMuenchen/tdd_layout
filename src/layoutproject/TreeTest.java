package layoutproject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


public class TreeTest {

	@Test
	public void noChildrenAndNode_BoxHasSizeDoubledSpaceAroundPlusNodeSize() {
		LayoutingTree layoutingTree = treeWithoutChildrenWithNodeSize(new Size(1,1));

		Box boundingBox = layoutingTree.getBoundingBox();
		
		assertThat(boundingBox, is(boxWithSize(1,1).addSpaceAround(LayoutingTree.SPACE_AROUND)));
	}
	
	@Test
	public void oneChildTreeWithHiddenRootNode_hasChildTreeBoundingBox() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(boxWithSize(1,1));
		
		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);
		
		Box boundingBox = parentTree.getBoundingBox();
		
		assertThat(boundingBox, is(boxWithSize(1,1)));
	}

	@Test
	public void oneChildTreeWithHiddenRootNode_setsChildTreePositionToItsOrigin() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(boxWithSize(1,1));
		
		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);
		
		Box boundingBox = parentTree.getBoundingBox();
		
		assertThat(boundingBox, is(boxWithSize(1,1)));
	}

	

	private Box boxWithSize(int width, int height) {
		return new Box(new Size(width, height), Position.ORIGIN);
	}

	private LayoutingTree treeWithoutChildrenWithNodeSize(Size size) {
		LayoutingTree layoutingTree = new LayoutingTree();
		Node node = new Node(size);
		layoutingTree.setRoot(node);
		return layoutingTree;
	}
	
	
}
