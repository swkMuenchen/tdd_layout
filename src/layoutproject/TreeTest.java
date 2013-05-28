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

    private void treeWithHiddenNodeAndChildHeight(int childHeight) {
        treeWithHiddenNodeAndChild(new Size(0, childHeight));
    }

    private void treeWithHiddenNodeAndChildWidth(int childWidth) {
        treeWithHiddenNodeAndChild(new Size(childWidth, 0));
    }

    private void treeWithHiddenNodeAndChild(Size size) {
        parentTree = new LayoutingTree();
        childTree = new StubTree(size);
        childTree.setX(-1);
        childTree.setY(-1);
        
        parentTree.setNode(Node.HIDDEN_ROOT);
        parentTree.addChild(childTree);
    }
    
    @Test
    public void noChildrenAndNode_TreeHasNodeHeight() {
        LayoutingTree layoutingTree = new LayoutingTree();
        Node node = new Node(ONE_X_ONE);
        layoutingTree.setNode(node);
        assertThat(layoutingTree.getHeight(), is(1));
    }

    @Test
    public void oneChildTreeWithHiddenNode_treeHasChildTreeHeight() {
        treeWithHiddenNodeAndChildHeight(1);
        assertThat(parentTree.getHeight(), is(1));
        
        treeWithHiddenNodeAndChildHeight(3);
        assertThat(parentTree.getHeight(), is(3));
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
	public void oneChildTreeWithHiddenNode_setsChildTreePositionToItsOrigin() {
        treeWithHiddenNodeAndChild(ONE_X_ONE);

        parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();

		assertThat(childTree.getPosition(), is(new Position(0, 0)));
	}


    @Test
    public void oneChildTreeWithEqualSizedNode_X() {
        setupOneChildTreeWithNodeAndChildHeight(1, 1);

        parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();
        //  NgggggC                                                                                      
        //  0123456
        assertThat(childTree.getPosition().getX(), is(6));
        assertThat(parentTree.getWidth(), is(7));
    }

    @Test
    public void oneChildTreeWithEqualSizedNode_Y() {
        setupOneChildTreeWithNodeAndChildHeight(1, 1);

        parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();
        // -3     CHILD                                                             
        // -2                                                                  
        // -1                                                                        
        //  0 NODE                                                                     
        //  1                                                                        
        assertThat(childTree.getPosition().getY(), is(-3));
        assertThat(parentTree.getHeight(), is(4));
    }

	@Test
    public void oneChildTreeWithDoubleSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(2, 1);

		parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();

        // -3     CHILD                                                             
        // -2                                                                  
        // -1                                                                        
        //  0 NODE                                                                     
        //  1 NODE 

        assertThat(childTree.getPosition().getY(), is(-3));
        assertThat(parentTree.getHeight(), is(5));
	}


	@Test
    public void oneChildTreeWithTrippleSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(3, 1);

		parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();
        // -2     CHILD                                                              
        // -1                                                                        
        //  0 NODE                                                                     
        //  1 NODE 
        //  2 NODE
        assertThat(childTree.getPosition().getY(), is(-2));
        assertThat(parentTree.getHeight(), is(5));
	}

	@Test
    public void oneChildTreeWith9foldSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(9, 1);

		parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();

        //  0 NODE                                                                     
        //  1 NODE   CHILD
        //  2 NODE ^
        //  3 NODE ^
        //  4 NODE ^
        //  5 NODE
        //  6 NODE
        //  7 NODE
        //  8 NODE
        assertThat(childTree.getPosition().getY(), is(1));
        assertThat(parentTree.getHeight(), is(9));
	}


	@Test
    public void oneTripleSizedChildTreeWithNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(1, 3);

		parentTree.layoutChildrenX();
        parentTree.layoutChildrenY();

        // -4     CHILD                                                                              
        // -3     CHILD                                                             
        // -2     CHILD                                                             
        // -1                                                                        
        //  0 NODE                                                                     

        assertThat(childTree.getPosition().getY(), is(-4));
        assertThat(parentTree.getHeight(), is(5));
	}

}
