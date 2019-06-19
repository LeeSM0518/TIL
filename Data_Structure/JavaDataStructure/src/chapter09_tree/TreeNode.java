package chapter09_tree;

public class TreeNode<T> {

    public T data;                      // 노드가 저장하는 자료
    public TreeNode<T> leftChild;       // 왼쪽 자식 노드
    public TreeNode<T> rightChild;      // 오른쪽 자식 노드
    public int number;              // 현재 노드의 번호

    public TreeNode(T data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }

    public TreeNode(T data, int number) {
        this.data = data;
        this.number = number;
    }
}