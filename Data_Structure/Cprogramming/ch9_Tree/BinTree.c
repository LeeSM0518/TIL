#include <stdio.h>
#include <stdlib.h>

typedef struct BinTreeNodeType	// 노드 타입
{
	char data;								// 노드가 저장하는 자료

	struct BinTreeNodeType* pLeftChild;		// 왼쪽 자식 노드를 가리키는 포인터 변수
	struct BinTreeNodeType* pRightChild;	// 오른쪽 자식 노드를 가리키는 포인터 변수
}BinTreeNode;

typedef struct BinTreeType	// 이진 트리
{
	struct BinTreeNodeType* pRootNode;		// 루트 노드를 가리키는 포인터 변수
}BinTree;

BinTreeNode* makeNewNodeBT(char data)	// 새로운 노드 생성
{
	BinTreeNode* pReturn = (BinTreeNode*)malloc(sizeof(BinTreeNode));
	// 노드 초기화
	if (pReturn != NULL) {
		pReturn->data = data;
		pReturn->pLeftChild = NULL;
		pReturn->pRightChild = NULL;
	}
	return pReturn;
}

BinTree* makeBinTree(char rootNodeData)		// 트리 생성
{
	BinTree *pReturn = NULL;
	pReturn = (BinTree*)malloc(sizeof(BinTree));
	if (pReturn != NULL) {
		// 트리의 루트 노드 지정
		pReturn->pRootNode = makeNewNodeBT(rootNodeData);
		if (pReturn->pRootNode == NULL) {
			free(pReturn);
			pReturn = NULL;
			printf("오류, 메모리 할당(2), makeBinTree()\n");
		}
	}
	else {
		printf("오류, 메모리 할당(1), makeBinTree()\n");
	}
	return pReturn;
}

BinTreeNode* addLeftChildeNodeBT(BinTreeNode* pParentNode, char data)	// 왼쪽 자식에 노드 추가
{
	BinTreeNode* pReturn = NULL;
	if (pParentNode != NULL) {
		if (pParentNode->pLeftChild == NULL) {					// 왼쪽 자식이 없다면
			pParentNode->pLeftChild = makeNewNodeBT(data);		// 왼쪽 자식에 노드 추가
			pReturn = pParentNode->pLeftChild;					// 리턴에 왼쪽 자식 노드 저장
		}
		else {
			printf("오류, 이미 노드가 존재합니다, addLeftChildNodeBT()\n");
		}
	}
	return pReturn;
}

BinTreeNode* addRightChildeNodeBT(BinTreeNode* pParentNode, char data)	// 오른쪽 자식에 노드 추가
{
	BinTreeNode* pReturn = NULL;
	if (pParentNode != NULL) {
		if (pParentNode->pRightChild == NULL) {					// 오른쪽 자식이 없다면
			pParentNode->pRightChild = makeNewNodeBT(data);		// 오른쪽 자식에 노드 추가
			pReturn = pParentNode->pRightChild;					// 리턴노드에 오른쪽 자식 노드 저장
		}
		else {
			printf("오류, 이미 노드가 존재합니다, addLeftChildNodeBT()\n");
		}
	}
	return pReturn;
}

BinTreeNode* getRootNodeBT(BinTree* pBinTree)		// 루트 노드 반환 함수
{
	BinTreeNode* pReturn = NULL;

	if (pBinTree != NULL) {
		pReturn = pBinTree->pRootNode;
	}
	return pReturn;
}

void deleteBinTreeNode(BinTreeNode* pNode) // 노드 제거 함수
{
	if (pNode != NULL) {
		deleteBinTreeNode(pNode->pLeftChild);		// 왼쪽 자식을 타고 레벨 1까지 타고 간다.
		deleteBinTreeNode(pNode->pRightChild);		// 오른쪽 자식을 타고 레벨 1까지 타고간다.
		free(pNode);
	}
}

void deleteBinTree(BinTree* pBinTree)		// 트리 삭제 함수
{	// 루트 노드와 루트 노드의 후손 노드들을 재귀적으로 삭제한다.
	if (pBinTree != NULL) {
		deleteBinTreeNode(pBinTree->pRootNode);
		free(pBinTree);
	}
}

int main(int argc, char *argv[])
{
	BinTree *pBinTree = NULL;
	BinTreeNode *pNodeA = NULL, *pNodeB = NULL, *pNodeC = NULL;
	BinTreeNode *pNodeD = NULL, *pNodeE = NULL, *pNodeF = NULL;

	pBinTree = makeBinTree('A');
	if (pBinTree != NULL) {
		pNodeA = getRootNodeBT(pBinTree);
		pNodeB = addLeftChildeNodeBT(pNodeA, 'B');
		pNodeC = addRightChildeNodeBT(pNodeA, 'C');
		if (pNodeB != NULL) {
			pNodeD = addLeftChildeNodeBT(pNodeB, 'D');
		}
		if (pNodeC != NULL) {
			pNodeE = addLeftChildeNodeBT(pNodeC, 'E');
			pNodeF = addRightChildeNodeBT(pNodeC, 'F');
		}
		deleteBinTree(pBinTree);
	}
	system("pause");
}