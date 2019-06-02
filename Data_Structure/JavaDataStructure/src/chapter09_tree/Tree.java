package chapter09_tree;

public class Tree {

    private TreeNode rootNode;      // 루트 노드

    // 생성자, 루트 노드 생성
    public <T> Tree(T data) {
        rootNode = new TreeNode<>(data);
    }

    // 부모의 왼쪽 자식 노드 추가시
    public <T> void addLeftChildNode(TreeNode parentNode, TreeNode newData) {
        if (parentNode != null && parentNode.leftChild == null) {
            parentNode.leftChild = newData;
        } else {
            System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
        }
    }

    // 부모의 오른쪽 자식 노드 추가시
    public <T> void addRightChildNode(TreeNode parentNode, TreeNode newData) {
        if (parentNode != null && parentNode.rightChild == null) {
            parentNode.rightChild = newData;
        } else {
            System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
        }
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void preorderTraversalRecursiveTree() {
        System.out.println("재귀 전위 순회");
        preorderTraversalRecursiveTreeNode(this.rootNode);
        System.out.println();
    }

    private void preorderTraversalRecursiveTreeNode(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);                          // V
            preorderTraversalRecursiveTreeNode(node.leftChild);     // L
            preorderTraversalRecursiveTreeNode(node.rightChild);    // R
        }
    }

    public void inorderTraversalRecursiveTree() {
        System.out.println("재귀 중위 순회");
        inorderTraversalRecursiveTreeNode(this.rootNode);
        System.out.println();
    }

    private void inorderTraversalRecursiveTreeNode(TreeNode node) {
        if (node != null) {
            inorderTraversalRecursiveTreeNode(node.leftChild);      // L
            System.out.println(node.data);                          // V
            inorderTraversalRecursiveTreeNode(node.rightChild);     // R
        }
    }

    public void postorderTraversalRecursiveTree() {
        System.out.println("재귀 후위 순회");
        postorderTraversalRecursiveTreeNode(this.rootNode);
        System.out.println();
    }

    private void postorderTraversalRecursiveTreeNode(TreeNode node) {
        if (node != null) {
            postorderTraversalRecursiveTreeNode(node.leftChild);      // L
            postorderTraversalRecursiveTreeNode(node.rightChild);     // R
            System.out.println(node.data);                            // V
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree('A');
        TreeNode[] treeNodes = new TreeNode[]{
                new TreeNode<>('B'),
                new TreeNode<>('C'),
                new TreeNode<>('D'),
                new TreeNode<>('E'),
                new TreeNode<>('F'),
                new TreeNode<>('G')
        };

        tree.addLeftChildNode(tree.getRootNode(), treeNodes[0]);
        tree.addRightChildNode(tree.getRootNode(), treeNodes[1]);

        tree.addLeftChildNode(treeNodes[0], treeNodes[2]);
        tree.addRightChildNode(treeNodes[0], treeNodes[3]);

        tree.addLeftChildNode(treeNodes[1], treeNodes[4]);
        tree.addRightChildNode(treeNodes[1], treeNodes[5]);


        tree.preorderTraversalRecursiveTree();
        tree.inorderTraversalRecursiveTree();
        tree.postorderTraversalRecursiveTree();
    }
}
