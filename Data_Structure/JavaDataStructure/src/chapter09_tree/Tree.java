package chapter09_tree;

public class Tree {

    private TreeNode rootNode;           // 루트 노드
    private int nodeCount;
    private int leftOrRight;
    private boolean searchSuccess;

    private final int LEFT = 0;
    private final int RIGHT = 1;

    // 생성자, 루트 노드 생성
    public <T> Tree(T data) {
        rootNode = new TreeNode<>(data, 0);
        nodeCount = 0;
        leftOrRight = LEFT;
        searchSuccess = false;
    }

    // 부모의 왼쪽 자식 노드 추가시
    public void addLeftChildNode(TreeNode parentNode, TreeNode newData) {
        if (parentNode != null && parentNode.leftChild == null) {
            parentNode.leftChild = newData;
        } else {
            System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
        }
    }

    // 부모의 오른쪽 자식 노드 추가시
    public void addRightChildNode(TreeNode parentNode, TreeNode newData) {
        if (parentNode != null && parentNode.rightChild == null) {
            parentNode.rightChild = newData;
        } else {
            System.out.println("에러, 이미 노드가 존재합니다. addLeftChildNode()");
        }
    }

    public <T> void sequentialAdd(T data) {
        searchSuccess = false;
        sequentialAddNode(this.getRootNode(), data);
        this.nodeCount++;
    }

    private <T> void sequentialAddNode(TreeNode node, T data) {
        if (searchSuccess) return;

        if (node != null) {
            if (node.number == nodeCount / 2) {
                if (leftOrRight == LEFT) {
                    node.leftChild = new TreeNode<>(data, nodeCount + 1);
                } else {
                    node.rightChild = new TreeNode<>(data, nodeCount + 1);
                }
                leftOrRight = (leftOrRight == LEFT) ? RIGHT : LEFT;
                searchSuccess = true;
                return;
            }
            sequentialAddNode(node.leftChild, data);       // L
            sequentialAddNode(node.rightChild, data);      // R
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
        char[] chars = new char[]{'B', 'C', 'D', 'E', 'F', 'G'};

        for (char node : chars) {
            tree.sequentialAdd(node);
        }

        tree.preorderTraversalRecursiveTree();
        tree.inorderTraversalRecursiveTree();
        tree.postorderTraversalRecursiveTree();
    }
}
