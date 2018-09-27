#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct CircularListNodeType{
    int data;
    struct CircularListNodeType *pLink;
}CircularListNode;

typedef struct CircularListType{
    int currentCount;
    CircularListNode headerNode;
}CircularList;

CircularList *createCircularList(){
    CircularList *pReturn = (CircularList*)malloc(sizeof(CircularList));
    memset(pReturn, 0, sizeof(CircularList));
    return pReturn;
}

int getCircularListData(CircularList* pList, int position){
    int i = 0;

    CircularListNode *pCurrentNode = &(pList->headerNode);
    for( i = 0 ; i <= position ; i++){
        pCurrentNode = pCurrentNode->pLink;
    }

    return pCurrentNode->data;
}

int addCircularListData(CircularList* pList, int position, int data){
    int i = 0;

    CircularListNode *pNewNode = NULL;
    CircularListNode *pPreNode = NULL;

    pNewNode = (CircularListNode*)malloc(sizeof(CircularListNode));
    pNewNode->data = data;

    pPreNode = &(pList->headerNode);
    for( i = 0 ; i < position ; i++){
        pPreNode = pPreNode->pLink;
    }

    pNewNode->pLink = pPreNode->pLink;
    pPreNode->pLink = pNewNode;

    pList->currentCount++;
    if(pNewNode->pLink == NULL){
        pNewNode->pLink = pNewNode;
    }

    return 0;
}

int removeCircularListData(CircularList* pList, int position){
    int i = 0;
    CircularListNode *pDelNode = NULL;
    CircularListNode *pPreNode = NULL;

    pPreNode = &(pList->headerNode);
    for( i = 0 ; i < position ; i++){
        pPreNode = pPreNode->pLink;
    }

    pDelNode = pPreNode->pLink;
    pPreNode->pLink = pDelNode->pLink;

    pList->currentCount--;
    if( 0 == pList->currentCount){
        pList->headerNode.pLink = NULL;
    }

    free(pDelNode);

    return 0;
}

void deleteCircularList(CircularList* pList){
    int i = 0;
    CircularListNode *pDelNode = NULL;
    CircularListNode *pPreNode = pList->headerNode.pLink;
    for( i = 0 ; i < pList->currentCount ; i++){
        pDelNode = pPreNode;
        pPreNode = pPreNode->pLink;

        free(pDelNode);
    }

    free(pList);
}

void displayCircularList(CircularList* pList){
    int i = 0;
    CircularListNode* pNode = NULL;

    pNode = pList->headerNode.pLink;
    if( 0 == pList->currentCount ){
        printf("자료가 없습니다\n");
    }
    else{
        printf("노드 개수 : %d\n", pList->currentCount);

        for( i = 0 ; i < pList->currentCount ; i++){
            printf("[%d] = %d\n", i, pNode->data);
            pNode = pNode->pLink;
        }
    }
}

int main(int argc, char *argv[]){
    CircularList *pList = NULL;

    pList = createCircularList();

    addCircularListData(pList, 0, 10);
    displayCircularList(pList);
    addCircularListData(pList, 0, 20);
    displayCircularList(pList);
    addCircularListData(pList, 1, 30);
    displayCircularList(pList);

    removeCircularListData(pList, 2);
    displayCircularList(pList);
    removeCircularListData(pList, 1);
    displayCircularList(pList);
    removeCircularListData(pList, 0);
    displayCircularList(pList);

    deleteCircularList(pList);

    return 0;
}