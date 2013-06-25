package layoutproject;

public class TestTreeBuilder {


    static LayoutingTree parentTree(Node node, Tree... childTrees) {
        LayoutingTree parentTree = new LayoutingTree();
        parentTree.setNode(node);
        for (Tree childTree : childTrees)
            parentTree.addChild(childTree);
        return parentTree;
    }

}
