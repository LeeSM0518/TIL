#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedqueue.h"
#include "arraygraph.h"

void traversalBFS(ArrayGraph* pGraph, int startNodeID)
{
    int i = 0;
    int nodeID = 0;
    LinkedQueue* pQueue = NULL;
    LinkedQueueNode* pQueueNode = NULL;
    int *pVisit = NULL;

    if(pGraph == NULL){
        return;
    }
    pQueue = createLinkedQueue();   // 큐 생성
    pVisit = malloc(sizeof(int) * pGraph->nodeCount);
    if(pQueue == NULL || pVisit == NULL){
        return;
    }
    // 노드 여부를 모두 방문 하지 않음으로 저장
    memset(pVisit, 0, sizeof(int) * pGraph->nodeCount);
    pVisit[startNodeID] = 1;
    enqueueLQ(pQueue, startNodeID);

    // 큐가 공백 상태가 될때까지 반복한다.
    // 큐에서 디큐 연산을 수행하여 꺼낸 노드에 대해 방문 정보를 출력함
    while(isLinkedQueueEmpty(pQueue) == 0){
        pQueueNode = dequeueLQ(pQueue);
        if(pQueueNode != NULL){
            nodeID = pQueueNode->data;
            printf("노드 - [%d] 방문\n", nodeID);
            // 디큐한 노드에 인접한 노드들에 대해서 처리함
            for(i = 0 ; i < pGraph->nodeCount ; i++){
                if(i != nodeID){
                    // 간선이 존재하는(인접한) 노드인지 확인
                    if( 0 != getEdgeAG(pGraph, nodeID, i)){
                        // 기존에 방문한 적이 없는 노드인지 확인
                        if( 0 == pVisit[i]){
                            // 방문 처리하고 큐에 인큐함
                            pVisit[i] = 1;
                            enqueueLQ(pQueue, i);
                        }
                    }
                }
            }
            free(pQueueNode);
        }
    }
    deleteLinkedQueue(pQueue);
    free(pVisit);
}