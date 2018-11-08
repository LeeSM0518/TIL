#include <stdio.h>
#include <stdlib.h>
#include "bintree.h"

BinTree* createExampleBinTree();
void preorderTraversalRecursiveBinTree(BinTree *pBinTree);
void inorderTraversalRecursiveBinTree(BinTree *pBinTree);
void postorderTraversalRecursiveBinTree(BinTree *pBinTree);

int main(int argc, char *argv[])
{
	BinTree *pBinTree = createExampleBinTree();
	if (pBinTree != NULL) {
		printf("전위 순회 결과: ");
		preorderTraversalRecursiveBinTree(pBinTree);
		printf("중위 순회 결과: ");
		inorderTraversalRecursiveBinTree(pBinTree);
		printf("후위 순회 결과: ");
		postorderTraversalRecursiveBinTree(pBinTree);

		deleteBinTree(pBinTree);
	}
	system("pause");
}

BinTree* createExampleBinTree() {
	BinTree *pBinTree = NULL;
	BinTreeNode *pNodeA = NULL, *pNodeB = NULL, *pNodeC = NULL;
	BinTreeNode *pNodeD = NULL, *pNodeE = NULL, *pNodeF = NULL, *pNodeG = NULL;

	pBinTree = makeBinTree('A');
	if (pBinTree != NULL) {
		pNodeA = getRootNodeBT(pBinTree);
		pNodeB = addLeftChildeNodeBT(pNodeA, 'B');
		pNodeC = addRightChildeNodeBT(pNodeA, 'C');
		if (pNodeB != NULL) {
			pNodeD = addLeftChildeNodeBT(pNodeB, 'D');
		}
		if (pNodeC != NULL) {
			pNodeE = addRightChildeNodeBT(pNodeB, 'E');
			pNodeF = addLeftChildeNodeBT(pNodeC, 'F');
		}
		if (pNodeF != NULL) {
			pNodeG = addRightChildeNodeBT(pNodeC, 'G');
		}
	}
	return pBinTree;
}