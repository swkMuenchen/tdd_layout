package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TreeTestX {

    private static final Size ONE_X_ONE = new Size(1, 1);
	private LayoutingTree parentTree;
	private StubTree childTree;

    private void setupOneChildTreeWithNodeAndChild() {
        childTree = new StubTree(1, 1);
        parentTree = TestTreeBuilder.parentTree(new Node(new Size(1, 1)), childTree);
	}

    private void treeWithHiddenNodeAndChildWidth(int childWidth) {
        childTree = new StubTree(childWidth, 0);
        childTree.setX(-1);
        parentTree = TestTreeBuilder.parentTree(Node.HIDDEN_ROOT, childTree);
    }

    @Test
    public void noChildrenAndNode_TreeHasNodeWidth() {
        LayoutingTree layoutingTree = new LayoutingTree();
        Node node = new Node(ONE_X_ONE);
        layoutingTree.setNode(node);

        assertThat(layoutingTree.getWidth(), is(1));
    }

    @Test
    public void oneChildTreeWithHiddenNode_treeHasChildTreeWidth() {
        treeWithHiddenNodeAndChildWidth(1);
        assertThat(parentTree.getWidth(), is(1));
        
        treeWithHiddenNodeAndChildWidth(2);
        assertThat(parentTree.getWidth(), is(2));
    }
    

	@Test
    public void oneChildTreeWithHiddenNode_setsChildTreeXPositionTo0() {
        treeWithHiddenNodeAndChildWidth(1);

        parentTree.layoutChildren();

        assertThat(childTree.getX(), is(0));
	}

    @Test
    public void oneChildTreeWithEqualSizedNode_X() {
        setupOneChildTreeWithNodeAndChild();

        parentTree.layoutChildren();

        //  NgggggC                                                                                      
        //  0123456
        assertThat(childTree.getX(), is(6));
        assertThat(parentTree.getWidth(), is(7));
    }
}
