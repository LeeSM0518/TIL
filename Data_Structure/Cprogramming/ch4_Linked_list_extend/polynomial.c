#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct TermType{    //  '항'을 나타내는 구조체
    double coef;    //  계수
    int degree;     //  차수
}Term;

typedef struct LinkedListNodeType{  //  노드를 나타내는 구조체
    Term data;  //  구조체 'Term' 타입의 자료
    struct LinkedListNodeType *pLink;
}LinkedListNode;

typedef struct LinkedListType{  //  다항식을 나타내는 구조체
    int currentCount;
    LinkedListNode headerNode;
}LinkedList, PolyList;

LinkedList *createLinkedList(){
    LinkedList *pReturn = (LinkedList*)malloc(sizeof(LinkedList));
    memset(pReturn, 0, sizeof(LinkedList));   // 새로 메모리를 할당한 다음에 0으로 초기화 ( 버그를 없애기 위함 )
    return pReturn;
}   // 리스트 생성

Term getLinkedListData(LinkedList* pList, int position){
    int i = 0;

    LinkedListNode *pCurrentNode = &(pList->headerNode);
    for( i = 0 ; i <= position; i++){
        pCurrentNode = pCurrentNode->pLink;
    }
    return pCurrentNode->data;
}

int addLinkedListData(LinkedList* pList, int position, Term data){
    int i = 0;
    LinkedListNode *pNewNode = NULL;
    LinkedListNode *pPreNode = NULL;

    pNewNode = (LinkedListNode*)malloc(sizeof(LinkedListNode));
    pNewNode->data = data;

    pPreNode = &(pList->headerNode);
    for( i = 0 ; i < position ; i++){
        pPreNode = pPreNode -> pLink;
    }

    pNewNode -> pLink = pPreNode -> pLink;
    pPreNode -> pLink = pNewNode;
    pList->currentCount++;
    return 1;
}

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

int addPolyNodeList(PolyList* pList, double coef, int degree)   //  다항식에 새로운 노드를 추가하는 함수 : 다항식에 새로운 항을 추가
{
    int ret = 0, position = 0;

    Term term = {0,};
    term.coef = coef;
    term.degree = degree;

    if(pList != NULL){
        position = pList->currentCount;
        ret = addLinkedListData(pList, position, term);
    }
    return ret;
}

void displayPolyList(PolyList* pList)
{
    int i = 0;
    LinkedListNode* pNode = NULL;
    pNode = pList->headerNode.pLink;
    if( 0 == pList->currentCount){
        printf("자료가 없습니다.\n");
    }
    else{
        for( i = 0 ; i < pList->currentCount; i++ ){    //  리스트를 순회하면서 연결 리스트에 저장된 각 항의 내용을 출력
            if( i > 0){
                printf(" + ");
            }
            printf("%.1fx^%d",pNode->data.coef, pNode->data.degree);
            pNode = pNode->pLink;
        }
        printf("\n");
    }
}

PolyList* polyAdd(PolyList* pListA, PolyList* pListB)
{
    PolyList* pReturn = NULL;
    LinkedListNode *pNodeA = NULL, *pNodeB = NULL;
    double coefSum = 0;

    if(pListA != NULL && pListB != NULL){
        pReturn = createLinkedList();
        if(pReturn == NULL){
            printf("메모리 할당 오류, polyAdd()\n");
            return NULL;
        }

        pNodeA = pListA -> headerNode.pLink;
        pNodeB = pListB -> headerNode.pLink;
        while(pNodeA != NULL && pNodeB != NULL){
            int degreeA = pNodeA->data.degree;
            int degreeB = pNodeB->data.degree;
            if(degreeA > degreeB){              //  (다항식 pListA의 차수) > (다항식 pListB의 차수) 인 경우
                coefSum = pNodeA->data.coef;    //  pNodeA의 계수를 넣어준다.
                addPolyNodeList(pReturn, coefSum, degreeA); //  계수를 더해주는 과정
                pNodeA = pNodeA->pLink; // pNodeA를 다음 노드로 이동
            }
            else if(degreeA == degreeB){              //  (다항식 pListA의 차수) == (다항식 pListB의 차수) 인 경우
                coefSum = pNodeA -> data.coef + pNodeB->data.coef;  // A와 B의 계수를 더하고
                addPolyNodeList(pReturn, coefSum, degreeA); //  더한 계수의 값을 넣는다.
                pNodeA = pNodeA->pLink; //  A의 다음 노드로 이동
                pNodeB = pNodeB->pLink; //  B의 다음 노드로 이동
            }
            else{                         //  (다항식 pListA의 차수) < (다항식 pListB의 차수) 인 경우
                coefSum = pNodeB->data.coef;    //  B의 계수를 넣고
                addPolyNodeList(pReturn, coefSum, degreeB);     // 계수를 더해준다.
                pNodeB = pNodeB->pLink;     //  B의 다음 노드로 이동
            }
        }

        while(pNodeA != NULL){  //  남은 노드가 있는지 마지막 후처리
            coefSum = pNodeA->data.coef;
            addPolyNodeList(pReturn, coefSum, pNodeA->data.degree);
            pNodeA = pNodeA->pLink;
        }

        while(pNodeB != NULL){  //  남은 노드가 있는지 마지막 후처리
            coefSum = pNodeB->data.coef;
            addPolyNodeList(pReturn, coefSum, pNodeB->data.degree);
            pNodeB = pNodeB ->pLink;
        }
    }
    else
    {
        printf("오류, NULL 다항식이 전달되었습니다, polyAdd()\n");
    }

    return pReturn;
}

int main(int argc, char *argv[])
{
    PolyList *pListA = NULL;
    PolyList *pListB = NULL;
    PolyList *pListC = NULL;

    pListA = createLinkedList();
    pListB = createLinkedList();

    if( pListA != NULL && pListB != NULL)
    {
        //  다항식 초기화
        //  pListA : 7x^6 + 3x^5 + 5x^2
        //  pListB : 1x^5 + 2x^4 + 3x^2 + 4x^0
        addPolyNodeList(pListA, 7, 6);
        addPolyNodeList(pListA, 3, 5);
        addPolyNodeList(pListA, 5, 2);
        printf("A 다항식 = ");
        displayPolyList(pListA);
        printf("\n");

        addPolyNodeList(pListB, 1, 5);
        addPolyNodeList(pListB, 2, 4);
        addPolyNodeList(pListB, 3, 2);
        addPolyNodeList(pListB, 4, 0);
        printf("B 다항식 = ");
        displayPolyList(pListB);
        printf("\n");

        pListC = polyAdd(pListA, pListB);
        if(pListC != NULL){
            printf("A + B 다항식 = ");
            displayPolyList(pListC);
             printf("\n");
            deleteLinkedList(pListC);
        }

        deleteLinkedList(pListA);
        deleteLinkedList(pListB);
    }
    return 0;
}