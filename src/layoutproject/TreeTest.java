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

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), is(new Position(0, 0)));
	}

	@Test
	public void oneChildTreeWithEqualSizedRootNode_setsTreeSize() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		int nodeHeight = 1;
		parentTree.setRoot(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		int childY = (nodeHeight - 1) / 2 - Node.Y_SHIFT;
		Size parentTreeSize = new Size(2 + Node.X_GAP, nodeHeight - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneChildTreeWithEqualSizedRootNode_setsChildPosition() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		parentTree.setRoot(new Node(ONE_X_ONE));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, -Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithDoubleSizedRootNode_setsChildPosition() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		int nodeHeight = 2;
		parentTree.setRoot(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, -Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithDoubleSizedRootNode_setsTreeSize() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		int nodeHeight = 2;
		parentTree.setRoot(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		int childY = (nodeHeight - 1) / 2 - Node.Y_SHIFT;
		Size parentTreeSize = new Size(2 + Node.X_GAP, nodeHeight - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneChildTreeWithTrippleSizedRootNode_setsChildPosition() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		int nodeHeight = 3;
		parentTree.setRoot(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, 1 - Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithTrippleSizedRootNode_setsTreeSize() {
		LayoutingTree parentTree = new LayoutingTree();
		StubTree childTree = new StubTree(ONE_X_ONE);

		int nodeHeight = 3;
		parentTree.setRoot(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		int childY = 1 - Node.Y_SHIFT;
		Size parentTreeSize = new Size(2 + Node.X_GAP, nodeHeight - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

}
