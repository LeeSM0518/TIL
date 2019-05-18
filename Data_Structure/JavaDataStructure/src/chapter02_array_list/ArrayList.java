package chapter02_array_list;

public class ArrayList {
    private int maxCount;           // 최대 자료 개수 : 배열의 크기
    private int currentCount = 0;       // 현재 자료 개수
    private Node[] nodes;           // 자료 저장을 위한 배열

    // 리스트 생성
    ArrayList(final int maxCount) {
        this.maxCount = maxCount;
        nodes = new Node[maxCount];
    }

    // 새로운 자료 추가
    public <T> void addNode(final int position, final T data) {
        // 추가되는 위치와 그 오른쪽에 있는 기존 자료를 모두 오른쪽으로 한 칸씩 이동
        for (int i = currentCount - 1; i >= position; i--) {
            nodes[i + 1] = nodes[i];
        }

        nodes[position] = new Node<>(data);   // 실제 자료 추가
        currentCount++;                     // 현재 저장된 자료 개수 1 증가
    }

    public <T, K> void addNode(final int position, final T key, final K value) {
        // 추가되는 위치와 그 오른쪽에 있는 기존 자료를 모두 오른쪽으로 한 칸씩 이동
        for (int i = currentCount - 1; i >= position; i--) {
            nodes[i + 1] = nodes[i];
        }

        nodes[position] = new PolyNode<>(key, value);   // 실제 자료 추가
        currentCount++;                     // 현재 저장된 자료 개수 1 증가
    }

    // 기존 자료의 제거
    public void removeNode(final int position) {
        // 제거되는 원소의 위치와 그 오른쪽으로 있는 원소를 왼쪽으로 한 칸씩 이동
        for (int i = position; i < currentCount - 1; i++) {
            nodes[i] = nodes[i + 1];
        }

        currentCount--;
    }

    // 값 가져오기
    public Node getNode(final int position) {

        if (position < currentCount || position >= 0)
            return nodes[position];
        else
            System.out.println("값이 존재하지 않는 위치입니다.");

        return null;
    }

    public int length() {
        return currentCount;
    }
}