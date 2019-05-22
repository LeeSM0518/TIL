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

BinTreeNode* makeNewNodeBT(char data);	// 새로운 노드 생성
BinTree* makeBinTree(char rootNodeData);		// 트리 생성
BinTreeNode* addLeftChildeNodeBT(BinTreeNode* pParentNode, char data);	// 왼쪽 자식에 노드 추가
BinTreeNode* addRightChildeNodeBT(BinTreeNode* pParentNode, char data);	// 오른쪽 자식에 노드 추가
BinTreeNode* getRootNodeBT(BinTree* pBinTree);		// 루트 노드 반환 함수
void deleteBinTreeNode(BinTreeNode* pNode); // 노드 제거 함수
void deleteBinTree(BinTree* pBinTree);		// 트리 삭제 함수