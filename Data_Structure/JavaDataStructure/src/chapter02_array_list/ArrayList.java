package chapter02_array_list;

public class ArrayList {
    int maxCount;           // 최대 자료 개수 : 배열의 크기
    int currentCount;       // 현재 자료 개수
    Node[] nodes;           // 자료 저장을 위한 배열

    // 리스트 생성
    ArrayList(int maxCount) {
        this.maxCount = maxCount;
        nodes = new Node[maxCount];
    }

    // 새로운 자료 추가
    void addListData(int position, int data) {
        int i = 0;

        try {
            // 추가되는 위치와 그 오른쪽에 있는 기존 자료를 모두 오른쪽으로 한 칸씩 이동
            for (i = currentCount - 1; i >= position; i--) {
                nodes[i+1].data = nodes[i].data;
            }
        } catch (NullPointerException ex) {
            nodes[i+1] = new Node(nodes[i].data);
        }

        nodes[position] = new Node(data);   // 실제 자료 추가
        currentCount++;                     // 현재 저장된 자료 개수 1 증가
    }

    // 기존 자료의 제거
    void removeListData(int position) {
        int i = 0;

        // 제거되는 원소의 위치와 그 오른쪽으로 있는 원소를 왼쪽으로 한 칸씩 이동
        for (i = position; i < currentCount - 1; i++) {
            nodes[i].data = nodes[i+1].data;
        }

        currentCount--;
    }

    // 값 가져오기
    int getListData(int position) {
        if (position < currentCount || position >= 0)
            return nodes[position].data;
        else
            System.out.println("값이 존재하지 않는 위치입니다.");
        return 0;
    }

}