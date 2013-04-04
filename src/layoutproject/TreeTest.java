package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TreeTest {

	private static final Size ONE_X_ONE = new Size(1, 1);

	@Test
	public void noChildrenAndNode_TreeHasNodeSize() {
		LayoutingTree layoutingTree = new LayoutingTree();
		Node node = new Node(ONE_X_ONE);
		layoutingTree.setRoot(node);
		assertThat(layoutingTree.getSize(), is(ONE_X_ONE));
	}

	@Test
	public void oneChildTreeWithHiddenRootNode_treeHasChildTreeSize() {
		oneChildTreeWithHiddenRootNode_hasGivenChildTreeSize(ONE_X_ONE);
		oneChildTreeWithHiddenRootNode_hasGivenChildTreeSize(new Size(2, 3));
	}

	private void oneChildTreeWithHiddenRootNode_hasGivenChildTreeSize(Size size) {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(size);

		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);

		assertThat(parentTree.getSize(), is(size));
	}

	@Test
	public void oneChildTreeWithHiddenRootNode_setsChildTreePositionToItsOrigin() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		parentTree.setRoot(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);

		assertThat(childTree.getPosition(), is(new Position(0, 0)));
	}

	@Test
	public void oneChildTreeWithVisibleRootNode_treeHasChildTreeSize() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		parentTree.setRoot(new Node(ONE_X_ONE));
		parentTree.addChild(childTree);

		Size parentTreeSize = new Size(2 + Node.XGAP, 1);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

}
