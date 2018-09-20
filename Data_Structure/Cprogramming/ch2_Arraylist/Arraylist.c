"""

"""

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct ArrayListNodeType{
    int data;
}ArrayListNode; // 노드 데이터 (저장할 데이터)

typedef struct ArrayListType{
    int maxCount; // 최대 자료 개수 : 배열의 크기
    int currentCount; // 현재 자료 개수
    ArrayListNode *pData;// 자료 저장을 위한 1차원 배열 ( 실제 자료 저장 )
}ArrayList; // 배열 리스트

ArrayList *createList(int count)
{// 내부적으로 자료를 저장할 배열의 크기 count를 입력 파라미타(매개변수)로 전달 받음.
    ArrayList *pReturn = (ArrayList*)malloc(sizeof(ArrayList)); // 구조체 ArrayList에 대한 메모리 할당
    pReturn -> maxCount = count; // maxCount에 count 파라미타를 저장
    pReturn -> currentCount = 0; // 배열 리스트를 새로 생성했기 때문에 현재 저장된 자료 개수는 0 이다.
    pReturn -> pData = (ArrayListNode*)malloc(sizeof(ArrayListNode)*count); // 실제 자료를 저장할 배열에 대해서 메모리 할당
    memset(pReturn->pData, 0, sizeof(ArrayListNode)*count); // 0으로 초기화 해준다.

    return pReturn;
}// 리스트 생성

int addListData(ArrayList* pList, int position, int data)
{   // currentCount -1 이 배열의 맨 오른쪽 위치 값이다. 왜냐하면 배열의 시작은 0부터 이기 때문이다.
    int i = 0;
    for(i = pList->currentCount - 1; i >= position; i--){ // 추가되는 위치와 오른쪽에 있는 기존 자료를 모두 오른쪽으로 한 칸씩 이동
        pList->pData[i+1] = pList->pData[i];
    }
    pList->pData[position].data = data; // 실제 자료 추가
    pList->currentCount++;  // 현재 저장된 자료 개수를 1개 추가

    return 0;
}// 노드 추가

int removeListData(ArrayList* pList,int position)
{
    int i = 0;
    for(i= position; i < pList -> currentCount -1;i++){ // 제거되는 원소의 위치와 그 오른쪽으로 있는 자료를 왼쪽으로 한 칸씩 옮기는 연산을 수행한다.
        pList->pData[i] = pList->pData[i+1];            // position 파라미타는 데이터를 삭제할 위치 이다.
    }
    pList->currentCount--;
    return 0;
}// 노드 제거

int getListData(ArrayList* pList, int position)
{
    return pList->pData[position].data; // 배열 리스트 pList의 배열 pData의 위치 position에 저장된 자료를 반환.
}// 노드 호출

void deleteList(ArrayList* pList)
{
    free(pList->pData); // pData가 배열을 가리키는 포인터이므로 할당된 배열의 메모리를 해제시켜줌.
    free(pList);        // 배열 리스트 자체에 대한 메모리 해제
}// 배열 리스트 삭제

int main(int argc, char *argv[])
{
    ArrayList *pList = NULL;    //pList 라는 리스트 초기화
    int value = 0;

    pList = createList(5); // pList에 5개 노드 생성
    addListData(pList, 0, 10);  // 첫 번째 노드에 10 저장
    addListData(pList, 1, 20);  // 두 번째 노드에 20 저장
    addListData(pList, 0, 30);  // 첫 번째 노드에 30 저장하고 원래 있던 첫 번째와 두 번째 노드를 뒤로 민다.

    value = getListData(pList, 1);  // 두 번째 값을 불러 온다.
    printf("위치 : %d, 값: %d\n",1,value); // 값 호출

    removeListData(pList, 0);   // 첫 번재 값 삭제
    deleteList(pList);          // pList 라는 리스트를 제거

    return 0;
}