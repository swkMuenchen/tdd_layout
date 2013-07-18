package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TreeTestY {

    private static final Size ONE_X_ONE = new Size(1, 1);
	private LayoutingTree parentTree;
	private StubTree childTree;
    private StubTree childTree1;
    private StubTree childTree2;

    private void setupOneChildTreeWithNodeAndChildHeight(int nodeHeight,
		int childHeight) {
        childTree = new StubTree(1, childHeight);
        parentTree = TestTreeBuilder.parentTree(new Node(new Size(1, nodeHeight)), childTree);
	}

    private void treeWithHiddenNodeAndChildHeight(int childHeight) {
        childTree = new StubTree(0, childHeight);
        childTree.setY(-1);
        parentTree = TestTreeBuilder.parentTree(Node.HIDDEN_ROOT, childTree);
    }

    private void hiddenNodeWithTwoChildHeights(int heightChild1, int heightChild2) {
        childTree1 = new StubTree(0, heightChild1);
        childTree2 = new StubTree(0, heightChild2);
        parentTree = TestTreeBuilder.parentTree(Node.HIDDEN_ROOT, childTree1, childTree2);
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
    public void oneSmallChildTreeWithHiddenNode() {
        // -1                                                                        
        //  0 [NODE] CHILD                                                                     
        //  1                                                                        
        treeWithHiddenNodeAndChildHeight(1);

        parentTree.layoutChildren();

        assertThat(childTree.getY(), is(0));
    }

    @Test
    public void oneTallChildTreeWithHiddenNode() {
        // -1        CHILD                                                                
        //  0 [NODE] CHILD                                                                     
        //  1        CHILD                                                                
        treeWithHiddenNodeAndChildHeight(3);

        parentTree.layoutChildren();

        assertThat(childTree.getY(), is(-1));
    }

    @Test
    public void oneChildTreeWithEqualSizedNode_Y() {
        setupOneChildTreeWithNodeAndChildHeight(1, 1);

        parentTree.layoutChildren();

        // -3     CHILD                                                             
        // -2                                                                  
        // -1                                                                        
        //  0 NODE                                                                     
        //  1                                                                        
        assertThat(childTree.getY(), is(-3));
        assertThat(parentTree.getHeight(), is(4));
    }

	@Test
    public void oneChildTreeWithDoubleSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(2, 1);

        parentTree.layoutChildren();

        // -3     CHILD                                                             
        // -2                                                                  
        // -1                                                                        
        //  0 NODE                                                                     
        //  1 NODE 

        assertThat(childTree.getY(), is(-3));
        assertThat(parentTree.getHeight(), is(5));
	}


	@Test
    public void oneChildTreeWithTrippleSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(3, 1);

        parentTree.layoutChildren();

        // -2     CHILD                                                              
        // -1                                                                        
        //  0 NODE                                                                     
        //  1 NODE 
        //  2 NODE
        assertThat(childTree.getY(), is(-2));
        assertThat(parentTree.getHeight(), is(5));
	}

	@Test
    public void oneChildTreeWith9foldSizedNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(9, 1);

        parentTree.layoutChildren();

        //  0 NODE                                                                     
        //  1 NODE   CHILD
        //  2 NODE ^
        //  3 NODE ^
        //  4 NODE ^
        //  5 NODE
        //  6 NODE
        //  7 NODE
        //  8 NODE
        assertThat(childTree.getY(), is(1));
        assertThat(parentTree.getHeight(), is(9));
	}


	@Test
    public void oneTripleSizedChildTreeWithNode_Y() {
		setupOneChildTreeWithNodeAndChildHeight(1, 3);

        parentTree.layoutChildren();

        // -4     CHILD
        // -3     CHILD
        // -2     CHILD
        // -1                                                              
        //  0 NODE

        assertThat(childTree.getY(), is(-4));
        assertThat(parentTree.getHeight(), is(5));
	}

    @Test
    public void oneNeunthSizedChildTreeWithNode_Y() {
        setupOneChildTreeWithNodeAndChildHeight(1, 9);

        parentTree.layoutChildren();

        // -7       CHILD 
        // -6       CHILD 
        // -5       CHILD
        // -4       CHILD
        // -3       CHILD
        // -2       CHILD
        // -1       CHILD                                                         
        //  0 NODE  CHILD
        //  1       CHILD   
        assertThat(childTree.getY(), is(-7));
        assertThat(parentTree.getHeight(), is(9));
    }

    @Test
    public void twoChildTreesWithHiddenNode() {
        // -1        CHILD1                                                         
        //  0 [NODE] VGAP=1
        //  1        CHILD2   
        hiddenNodeWithTwoChildHeights(1, 1);

        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-1));
        assertThat(childTree2.getY(), is(1));
        assertThat(parentTree.getHeight(), is(3));
    }

    @Test
    public void twoChildTreesWithDoubleHeightAndHiddenNode() {
        // -2        CHILD1                                                         
        // -1        CHILD1                                                         
        //  0 [NODE] Y_GAP
        //  1        CHILD2   
        //  2        CHILD2   
        hiddenNodeWithTwoChildHeights(2, 2);

        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-2));
        assertThat(childTree2.getY(), is(1));
        assertThat(parentTree.getHeight(), is(5));
    }

    @Test
    public void twoChildTreesWithDifferentHeightsAndHiddenNode() {
        // -3         CHILD1                                                         
        // -2         CHILD1                                                         
        // -1         Y_GAP
        //  0 [NODE]  CHILD2   
        //  1         CHILD2   
        //  2         CHILD2   
        hiddenNodeWithTwoChildHeights(2, 3);

        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-3));
        assertThat(childTree2.getY(), is(0));
        assertThat(parentTree.getHeight(), is(6));
    }

    @Test
    public void twoChildTreesWithSingleHeightAndVisibleNode() {
        // -1        CHILD1                                                         
        //  0 NODE Y_GAP
        //  1        CHILD2   
        childTree1 = new StubTree(0, 1);
        childTree2 = new StubTree(0, 1);
        parentTree = TestTreeBuilder.parentTree(new Node(ONE_X_ONE), childTree1, childTree2);

        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-1));
        assertThat(childTree2.getY(), is(1));
        assertThat(parentTree.getHeight(), is(3));
    }

    @Test
    public void threeChildTreesWithSingleHeightAndVisibleNode() {
        // -2        CHILD1                                                         
        // -1      Y_GAP
        //  0 NODE   CHILD2                                                        
        //  1      Y_GAP
        //  2        CHILD3   
        childTree1 = new StubTree(0, 1);
        childTree2 = new StubTree(0, 1);
        Tree childTree3 = new StubTree(0, 1);
        parentTree = TestTreeBuilder.parentTree(new Node(ONE_X_ONE), childTree1, childTree2, childTree3);

        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-2));
        assertThat(childTree2.getY(), is(0));
        assertThat(childTree3.getY(), is(2));
        assertThat(parentTree.getHeight(), is(5));
    }

    @Test
    public void oneShiftedChildTreeWithEqualSizedNode_Y() {
        // -3                                                                  
        // -2     CHILD[shift_y+=1]                                                             
        // -1                                                                        
        //  0 NODE                                                                     
        setupOneChildTreeWithNodeAndChildHeight(1, 1);
        childTree.getNode().setYShift(1);

        parentTree.layoutChildren();

        assertThat(childTree.getY(), is(-2));
        assertThat(parentTree.getHeight(), is(3));
    }

    @Test
    public void twoShiftedChildTreesWithSingleHeightAndVisibleNode() {
        // 0  NODE      CHILD1 [shiftY+=1]                                                         
        // 1          Y_GAP
        // 2            CHILD2   
        childTree1 = new StubTree(0, 1);
        childTree2 = new StubTree(0, 1);
        parentTree = TestTreeBuilder.parentTree(new Node(ONE_X_ONE), childTree1, childTree2);

        childTree1.getNode().setYShift(1);
        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(0));
        assertThat(childTree2.getY(), is(2));
        assertThat(parentTree.getHeight(), is(3));
    }

    @Test
    public void secondChildTreeShifted() {
        // -1         CHILD1                                                         
        // 0  NODE    Y_GAP                                                         
        // 1          
        // 2          CHILD2 [shiftY+=1]  
        childTree1 = new StubTree(0, 1);
        childTree2 = new StubTree(0, 1);
        parentTree = TestTreeBuilder.parentTree(new Node(ONE_X_ONE), childTree1, childTree2);

        childTree2.getNode().setYShift(1);
        parentTree.layoutChildren();

        assertThat(childTree1.getY(), is(-1));
        assertThat(childTree2.getY(), is(2));
        assertThat(parentTree.getHeight(), is(4));
    }

    @Test
    public void oneNegativelyShiftedChildTreeWithEqualSizedNode_Y() {
        // -4     CHILD[shift_y-=1]                                                             
        // -3                                                                  
        // -2                                                                  
        // -1                                                                        
        //  0 NODE                                                                     
        setupOneChildTreeWithNodeAndChildHeight(1, 1);
        childTree.getNode().setYShift(-1);

        parentTree.layoutChildren();

        assertThat(childTree.getY(), is(-4));
        assertThat(parentTree.getHeight(), is(5));
    }

}
