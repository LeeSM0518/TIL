#include <stdio.h>
#include <stdlib.h>

typedef struct BinTreeNodeType
{
	char data;  // 노드가 저장하는 자료

	struct BinTreeNodeType* pLeftChild;   // 왼쪽 자식 노드를 가리키는 포인터 변수
	struct BintTreeNodeType* pRightChild; // 오른쪽 자식 노드를 가리키는 포인터 변수
}BinTreeNode;

typedef struct BinTreeType
{
	struct BinTreeNodeType* pRootNode;    // 루트 노드를 가리키는 포인터 변수
}BinTree;


BinTreeNode* makeNewNodeBT(char data)  // 새로운 노드 추가
{
	BinTreeNode* pReturn = (BinTreeNode*)malloc(sizeof(BinTreeNode)); // 메모리 할당
	if (pReturn != NULL) {
		pReturn->data = data;
		pReturn->pLeftChild = NULL;
		pReturn->pRightChild = NULL;
	}
	return pReturn;
}

BinTree* makeBinTree(char rootNodeData) // 이진 트리 생성, 루트 노드 또한 생성
{
	BinTree *pReturn = NULL;
	pReturn = (BinTree*)malloc(sizeof(BinTree));    // 메모리 할당
	if (pReturn != NULL) {  // 검증
		pReturn->pRootNode = makeNewNodeBT(rootNodeData);
		if (pReturn->pRootNode == NULL) {
			free(pReturn);
			pReturn == NULL;
			printf("오류, 메모리 할당(2), makeBinTree()\n");
		}
	}
	else {
		printf("오류, 메모리 할당(1), makeBinTree()\n");
	}
	return pReturn;
}

BinTreeNode* addLeftChildNodeBT(BinTreeNode* pParentNode, char data)  // 왼쪽 자식 노드 추가
{
	BinTreeNode* pReturn = NULL;
	if (pParentNode != NULL) {    // 유효성 점검
		if (pParentNode->pLeftChild == NULL) {  // 새로운 노드를 할당받고 부모 노드 pParentNode의 왼쪽 자식 노드로 설정한다.
			pParentNode->pLeftChild = makeNewNodeBT(data);
		}
		else {
			printf("오류, 이미 노드가 존재합니다, addLeftChildNodeBT()\n");
		}
	}
	return pReturn;
}

BinTreeNode* addRightChildNodeBT(BinTreeNode* pParentNode, char data)  // 오른쪽에 자식 노드 추가
{
	BinTreeNode* pReturn = NULL;
	if (pParentNode != NULL) {    // 유효성 점검
		if (pParentNode->pRightChild == NULL) {  // 새로운 노드를 할당받고 부모 노드 pParentNode의 오른쪽 자식 노드로 설정한다.
			pParentNode->pRightChild = makeNewNodeBT(data);
		}
		else {
			printf("오류, 이미 노드가 존재합니다, addLeftChildNodeBT()\n");
		}
	}
	return pReturn;
}

BinTreeNode* getRootNodeBT(BinTree* pBinTree)   // 루트 반환 함수
{
	BinTreeNode* pReturn = NULL;

	if (pBinTree != NULL) {
		pReturn = pBinTree->pRootNode;
	}
	return pReturn;
}

void deleteBinTreeNode(BinTreeNode* pNode)  // 후손 노드들 모두를 재귀적으로 삭제
{
	if (pNode != NULL) {
		deleteBinTreeNode(pNode->pLeftChild);
		deleteBinTreeNode(pNode->pRightChild);
		free(pNode);
	}
}

void deleteBinTree(BinTree* pBinTree)   // 이진 트리 삭제
{
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
		pNodeB = addLeftChildNodeBT(pNodeA, 'B');
		pNodeC = addRightChildNodeBT(pNodeA, 'C');
		if (pNodeB != NULL) {
			pNodeD = addLeftChildNodeBT(pNodeB, 'D');
		}
		if (pNodeC != NULL) {
			pNodeE = addLeftChildNodeBT(pNodeC, 'E');
			pNodeF = addRightChildNodeBT(pNodeC, 'F');
		}
		deleteBinTree(pBinTree);
	}
	return 0;
}