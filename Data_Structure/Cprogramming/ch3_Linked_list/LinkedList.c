#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct LinkedListNodeType{
    int data;   // 자료 부분
    struct LinkedListNodeType *pLink;   // 연결부분
}LinkedListNode;

typedef struct LinkedListType{
    int     currentCount;   // 현재 노드 개수
    LinkedListNode headerNode;  // 헤더 노드 (다음 노드에 대한 링크를 저장하는 노드)
}LinkedList;    // 리스트 생성 하기 위한 헤더

LinkedList *createLinkedList(){
    LinkedList *pReturn = (LinkedList*)malloc(sizeof(LinkedList));
    memset(pReturn, 0, sizeof(LinkedList));   // 새로 메모리를 할당한 다음에 0으로 초기화 ( 버그를 없애기 위함 )
    return pReturn;
}   // 리스트 생성

int getLinkedListData(LinkedList* pList, int position){
    int i = 0;

    LinkedListNode *pCurrentNode = &(pList -> headerNode);  // pCurrentNode라는 구조체를 만들어주고 파라미타로 받은 리스트의 헤더 주소를 넣는다.
    for( i = 0 ; i <= position; i++ ){      // position +1 횟수만큼 노드의 링크를 이용하여 다음 노드로 이동한다.
        pCurrentNode = pCurrentNode -> pLink;
    }

    return pCurrentNode -> data;
}   // 리스트의 값 불러오기

int addLinkedListData(LinkedList* pList, int position, int data){
    int i = 0;
    LinkedListNode *pNewNode = NULL;
    LinkedListNode *pPreNode = NULL;

    pNewNode = (LinkedListNode*)malloc(sizeof(LinkedListNode));
    pNewNode->data = data;

    pPreNode = &(pList->headerNode);    // pPreNode에 리스트의 헤더노드 주소를 가져온다.
    for( i = 0 ; i < position ; i++ ){
        pPreNode = pPreNode -> pLink;
    }   // 새로 자료를 추가하려는 위치의 이전 노드까지 이동 시킨다.

    pNewNode->pLink = pPreNode->pLink;  // 새로운 노드의 링크를 이전의 노드가 가르키던 다음의 노드의 링크를 가르키게 한다.
    pPreNode->pLink = pNewNode;     // 이전의 노드의 링크를 새로운 노드를 가르키게 한다.
    pList->currentCount++;  // 현재 노드의 개수를 1 증가 시킨다.
    return 0;
}   // 새로운 노드 생성

int removeLinkedListData(LinkedList* pList, int position){
    int i = 0;
    LinkedListNode *pDelNode = NULL;
    LinkedListNode *pPreNode = NULL;

    pPreNode = &(pList->headerNode);
    for( i = 0 ; i < position ; i++){
        pPreNode = pPreNode -> pLink;
    } // 제거하려는 노드의 이전 노드까지 이동

    pDelNode = pPreNode -> pLink;   // 제거하려는 노드 지정 ( 지정된 pPreNode의 다음 링크가 제거하려는 노드이다.)
    pPreNode -> pLink = pDelNode -> pLink;  // 이전 노드의 처리 ( 이전 노드의 링크를 지울려는 링크의 다음 링크를 가르키게 한다.)
    free(pDelNode); // 메모리 해제
    pList->currentCount--; // 현재 노드 개수 1개 감소
    return 0;
}   // 기존 노드 제거

void deleteLinkedList(LinkedList* pList){
    LinkedListNode *pDelNode = NULL;
    LinkedListNode *pPreNode = pList -> headerNode.pLink;
    while(pPreNode != NULL){
        pDelNode = pPreNode;
        pPreNode = pPreNode-> pLink;

        free(pDelNode);
    }   // 각노드를 처음부터 순서대로 메모리 해체 시킨다.

    free(pList);    // 연결 리스트 자체를 메모리 해체 시킨다.
}   // 리스트 제거

int getLinkedListLength(LinkedList* pList){
    if(NULL != pList){
        return pList->currentCount;
    }
    return 0;
}   // 리스트의 길이 호출

void displayList(LinkedList *pList){
    int i = 0;
    printf("일반 순회\n");
    for( i = 0 ; i < pList->currentCount; i++){
        printf("[%d] = %d\n",i, getLinkedListData(pList,i));
    }
    printf("\n");
}   //  순회 : 연결 리스트의 노드를 차례대로 방문하는 것. O(n^2) 의 시간

void iterateLinkedList(LinkedList* pList)
{
    int count = 0;
    LinkedListNode* pNode = NULL;

    pNode = pList->headerNode.pLink;

    printf("로직 처리 순회\n");
    while(pNode != NULL){
        printf("[%d] = %d\n", count, pNode->data);  // 로직 처리 부분 : 헤더 노드의 링크를 가져와서 while 루프에서 데이터를 처리한다.
        count++;
        pNode = pNode->pLink;
    }
    printf("노드 개수 : %d\n",count);

}   //  루프 내에서 로직을 처리하도록 한 순회

void concatLinkedList(LinkedList* pListA, LinkedList* pListB){
    LinkedListNode* pNodeA = NULL;   // 연결 리스트 pListA의 마지막 노드를 가리킬 pNodeA

    if(pListA != NULL && pListB != NULL){   // 입력 파라미터 유효성 점검
        pNodeA = pListA -> headerNode.pLink;    // pNodeA가 연결리스트 pListA의 첫 번째 노드를 가리키게 한다.

        while(pNodeA != NULL && pNodeA->pLink != NULL){ // pNodeA가 마지막 노드를 가리킬 때까지 루프를 돈다.
            pNodeA = pNodeA->pLink;
        }
        pNodeA->pLink = pListB->headerNode.pLink;   // pNodeA의 다음 노드로 pListB의 첫 번째 노드를 설정한다.
        pListB->headerNode.pLink = NULL;    // 연결 리스트 pListB는 빈 연결 리스트가 되도록 한다.
    }
}   // 리스트와 리스트를 연결해주는 함수

int main(int argc, char *argv[]){
    LinkedList *pList = NULL;
    LinkedList *pListA = NULL;
    LinkedList *pListB = NULL;
    int value = 0;

    pList = createLinkedList();
    addLinkedListData(pList, 0, 10);
    addLinkedListData(pList, 1, 20);
    addLinkedListData(pList, 1, 30);

    value = getLinkedListData(pList,1);
    printf("위치 : %d\n값 : %d\n\n", 1, value);

    displayList(pList);

    iterateLinkedList(pList);

    removeLinkedListData(pList, 0);
    deleteLinkedList(pList);

    pListA = createLinkedList();
    pListB = createLinkedList();
    addLinkedListData(pListA, 0, 10);
    addLinkedListData(pListA, 1, 20);
    addLinkedListData(pListA, 2, 30);

    addLinkedListData(pListB, 0, 40);
    addLinkedListData(pListB, 1, 50);

    printf("연결 리스트 결합 전\n");
    printf("\n연결 리스트 A\n");
    iterateLinkedList(pListA);
    printf("\n연결 리스트 B\n");
    iterateLinkedList(pListB);

    concatLinkedList(pListA, pListB);
    printf("\n연결 리스트 결합 후\n");
    printf("\n연결 리스트 A\n");
    iterateLinkedList(pListA);
    printf("\n연결 리스트 B\n");
    iterateLinkedList(pListB);

    deleteLinkedList(pListA);
    deleteLinkedList(pListB);

    return 0;
}

/* 연결리스트

구성
    >> 자료를 저장하는 부분
    >> 자료 사이를 연결하는 부분 ( 포인터로 이루어지며 연결 정보(링크)를 가지고 있다.)

특징
    >> 맨 마지막 노드는 다음으로 연결된 노드가 없기 때문에 노드의 링크 pLink의 값이 "NULL" 이 된다.

연결 리스트의 사용 시나리오
    1. 연결 리스트를 만든다.
    2. 자료 10을


*/
