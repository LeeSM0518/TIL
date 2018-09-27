#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct DoublyListNodeType{
    int data;
    struct DoublyListNodeType *pLLink;
    struct DoublyListNodeType *pRLink;
}DoublyListNode;

typedef struct DoublyListType{
    int currentCount;
    DoublyListNode headerNode;
}DoublyList;

DoublyList *createDoublyList(){
    DoublyList *pReturn = (DoublyList*)malloc(sizeof(DoublyList));
    if(pReturn != NULL){
        memset(pReturn, 0, sizeof(DoublyList));

        pReturn->headerNode.pLLink = &(pReturn->headerNode);
        pReturn->headerNode.pRLink = &(pReturn->headerNode);
    }

    return pReturn;
}

int getDoublyListData(DoublyList* pList, int position){
    int i = 0;

    DoublyListNode *pCurrentNode = &(pList->headerNode);
    for( i = 0 ; i <= position ; i++){
        pCurrentNode = pCurrentNode->pRLink;
    }

    return pCurrentNode->data;
}

int addDoublyListData(DoublyList* pList, int position, int data){
    int i = 0;
    int ret = 0;

    DoublyListNode *pNewNode = NULL;
    DoublyListNode *pPreNode = NULL;

    pNewNode = (DoublyListNode*)malloc(sizeof(DoublyListNode));

    if(pNewNode != NULL){
        memset(pNewNode, 0, sizeof(DoublyListNode));
        pNewNode->data = data;

        pPreNode = &(pList->headerNode);
        for( i = 0 ; i < position ; i++){
            pPreNode = pPreNode->pRLink;
        }

        pNewNode->pRLink = pPreNode->pRLink;
        pNewNode->pLLink = pPreNode;

        pPreNode->pRLink = pNewNode;
        pNewNode->pRLink->pLLink = pNewNode;

        pList->currentCount++;
    }
    else{
        printf("오류, 메모리 할당 addListData()\n");
        return 1;
    }

    return ret;
}

int removeDoublyListData(DoublyList* pList, int position){
    int i = 0, ret = 0;
    DoublyListNode *pDelNode = NULL;
    DoublyListNode *pPreNode = NULL;

    pPreNode = &(pList->headerNode);
    for( i = 0 ; i < position ; i++){
        pPreNode = pPreNode->pRLink;
    }

    pDelNode = pPreNode->pRLink;
    pPreNode->pRLink = pDelNode->pRLink;

    pDelNode->pRLink->pLLink = pPreNode;

    pList->currentCount--;
    free(pDelNode);

    return ret;
}

void deleteDoublyList(DoublyList* pList){
    int i = 0;

    if(pList != NULL){
        while(pList->currentCount > 0){
            removeDoublyListData(pList, 0);
        }
        free(pList);
    }
}

int getDoublyListLength(DoublyList* pList){
    int ret = 0;
    if(NULL != pList){
        return pList->currentCount;
    }
    return ret;
}

void displayDoublyList(DoublyList* pList){
    int i = 0;
    DoublyListNode* pNode = NULL;

    pNode = pList->headerNode.pRLink;
    if( 0 == pList->currentCount ){
        printf("자료가 없습니다\n");
    }
    else{
        printf("노드 개수 : %d\n", pList->currentCount);

        for( i = 0 ; i < pList->currentCount ; i++){
            printf("[%d] = %d\n", i, pNode->data);
            pNode = pNode->pRLink;
        }
    }
}

int main(int argc, char *argv[]){
    DoublyList *pList = NULL;

    pList = createDoublyList();

    addDoublyListData(pList, 0, 10);
    displayDoublyList(pList);
    addDoublyListData(pList, 0, 20);
    displayDoublyList(pList);
    addDoublyListData(pList, 1, 30);
    displayDoublyList(pList);

    removeDoublyListData(pList, 2);
    displayDoublyList(pList);
    removeDoublyListData(pList, 1);
    displayDoublyList(pList);
    removeDoublyListData(pList, 0);
    displayDoublyList(pList);

    deleteDoublyList(pList);

    return 0;
}