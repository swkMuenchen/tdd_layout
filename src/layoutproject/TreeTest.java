package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TreeTest {

	private static final Size ONE_X_ONE = new Size(1, 1);
	private LayoutingTree parentTree;
	private StubTree childTree;

	private void setupOneChildTreeWithNodeAndChildHeight(int nodeHeight,
		int childHeight) {
		parentTree = new LayoutingTree();
		childTree = new StubTree(new Size(1, childHeight));

		parentTree.setNode(new Node(new Size(1, nodeHeight)));
		parentTree.addChild(childTree);
	}

	@Test
	public void noChildrenAndNode_TreeHasNodeSize() {
		LayoutingTree layoutingTree = new LayoutingTree();
		Node node = new Node(ONE_X_ONE);
		layoutingTree.setNode(node);
		assertThat(layoutingTree.getSize(), is(ONE_X_ONE));
	}

	@Test
	public void oneChildTreeWithHiddenNode_treeHasChildTreeSize() {
		oneChildTreeWithHiddenNode_hasGivenChildTreeSize(ONE_X_ONE);
		oneChildTreeWithHiddenNode_hasGivenChildTreeSize(new Size(2, 3));
	}

	private void oneChildTreeWithHiddenNode_hasGivenChildTreeSize(Size size) {
		parentTree = new LayoutingTree();
		childTree = new StubTree(size);

		parentTree.setNode(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);

		assertThat(parentTree.getSize(), is(size));
	}

	@Test
	public void oneChildTreeWithHiddenNode_setsChildTreePositionToItsOrigin() {
		parentTree = new LayoutingTree();
		childTree = new StubTree(ONE_X_ONE);

		parentTree.setNode(Node.HIDDEN_ROOT);
		parentTree.addChild(childTree);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), is(new Position(0, 0)));
	}

	@Test
	public void oneChildTreeWithEqualSizedNode_setsChildPosition() {
		setupOneChildTreeWithNodeAndChildHeight(1, 1);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, -Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithEqualSizedNode_setsTreeSize() {
		setupOneChildTreeWithNodeAndChildHeight(1, 1);

		parentTree.layoutChildren();

		int childY = (1 - 1) / 2 - Node.Y_SHIFT;
		Size parentTreeSize = new Size(2 + Node.X_GAP, 1 - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneChildTreeWithDoubleSizedNode_setsChildPosition() {
		setupOneChildTreeWithNodeAndChildHeight(2, 1);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, -Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithDoubleSizedNode_setsTreeSize() {
		setupOneChildTreeWithNodeAndChildHeight(2, 1);

		parentTree.layoutChildren();

		int childY = (2 - 1) / 2 - Node.Y_SHIFT;
		Size parentTreeSize = new Size(2 + Node.X_GAP, 2 - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneChildTreeWithTrippleSizedNode_setsChildPosition() {
		setupOneChildTreeWithNodeAndChildHeight(3, 1);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, 1 - Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWithTrippleSizedNode_setsTreeSize() {
		int nodeHeight = 3;
		setupOneChildTreeWithNodeAndChildHeight(nodeHeight, 1);

		parentTree.layoutChildren();

		int childY = childTree.getPosition().getY();
		Size parentTreeSize = new Size(2 + Node.X_GAP, nodeHeight - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneChildTreeWith9foldSizedNode_setsChildPosition() {
		setupOneChildTreeWithNodeAndChildHeight(9, 1);

		parentTree.layoutChildren();

		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, 4 - Node.Y_SHIFT)));
	}

	@Test
	public void oneChildTreeWith9foldSizedNode_setsTreeSize() {
		int nodeHeight = 9;
		setupOneChildTreeWithNodeAndChildHeight(nodeHeight, 1);

		parentTree.layoutChildren();

		Size parentTreeSize = new Size(2 + Node.X_GAP, nodeHeight);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

	@Test
	public void oneTripleSizedChildTreeWithNode() {
        // -5                                                                        
        // -4                 CHILD_                                                                  
        // -3     |----GAP---|CHILD_                                                 
        // -2                 CHILD_                                                 
        // -1                                                                        
        //  0NODE 1     2     3     4     5     6                                     
        //  1                                                                        

        // -5                                                                        
        // -4     CHILD_                                                                              
        // -3     CHILD_                                                             
        // -2     CHILD_                                                             
        // -1                                                                        
        //  0 NODE                                                                     
        //  1                                                                        

		int nodeHeight = 1;
		int childHeight = 3;
		setupOneChildTreeWithNodeAndChildHeight(nodeHeight, childHeight);

		parentTree.layoutChildren();

		int halfHeightDifference = (childHeight - nodeHeight) / 2;
		int childY = -halfHeightDifference - Node.Y_SHIFT;
		assertThat(childTree.getPosition(), //
			is(new Position(1 + Node.X_GAP, childY)));

		Size parentTreeSize = new Size(2 + Node.X_GAP, //
			nodeHeight - childY);
		assertThat(parentTree.getSize(), is(parentTreeSize));
	}

}
