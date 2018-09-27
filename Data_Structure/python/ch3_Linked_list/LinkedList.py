class LinkedListNode:
    def LinkedListNode(self):
        self.data = 0
        self.pLink = {'HeaderClass':0, 'CurrentClass': 0, 'NextClass':0 }
        self.pLink['CurrentClass'] = self
        self.currentCount = 0

def LinkedList(Node):
    LinkedListNode()
    Node.pLink['HeaderClass'] = Node
    return Node

def createLinkedList(Node):
    Node = LinkedListNode()
    Node.LinkedListNode()
    Node.data = 'HeaderNode'
    LinkedList(Node)
    return Node

def getLinkedListData(pList, position):
    i = 0
    pCurrentNode = LinkedListNode()
    pCurrentNode.LinkedListNode()
    pCurrentNode = pList.pLink['HeaderClass']

    if position > pList.currentCount :
        print('\n불러올 위치 값이 현재 노드 개수 보다 많습니다.\n')
        return

    for i in range(position):
        pCurrentNode = pCurrentNode.pLink['NextClass']

    return pCurrentNode.data

def addLinkedListData(pList, position, data):
    i = 0

    if position == 0 :
        print('\n값을 넣을 위치는 헤더노드의 위치 입니다.\n')

    pNewNode = LinkedListNode()
    pNewNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pNewNode.data = data

    pPreNode = pList.pLink['HeaderClass']
    for i in range(position-1) :
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pNewNode

    pList.currentCount += 1

    return pList

def removeLinkedListData(pList, position):
    if position == 0 :
        print('\n헤더노드는 지울 수 없습니다.\n')
        return
    i = int(0)
    pDelNode = LinkedListNode()
    pDelNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in range(position - 1):
        pPreNode = pPreNode.pLink['NextClass']

    pDelNode = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pDelNode.pLink['NextClass']

    pList.currentCount -= 1

def deleteLinkedList(pList):
    if pList.pLink == {} :
        print('\n리스트가 비어있습니다.\n')
        return

    pDelNode = LinkedListNode()
    pDelNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pPreNode = pList.pLink['HeaderClass']

    while(pPreNode.pLink['NextClass'] != 0 ):
        pDelNode = pPreNode
        pPreNode = pPreNode.pLink['NextClass']

        pDelNode.data = 0
        pDelNode.pLink.clear()

def getLinkedListLength(pList):
       return pList.pLink['HeaderClass'].currentCount

def displayList(pList):
    i = 0
    print('\n일반 순회')
    for i in range(pList.currentCount):
        print("Node[{}] = {}".format(i+1,getLinkedListData(pList,i+1)))

def iterateLinkedList(pList):
    count = int(0)
    pNode = LinkedListNode()
    pNode.LinkedListNode()

    if pList.pLink == {} :
        print('\n리스트가 비어있습니다.\n')
        return

    pNode = pList.pLink['HeaderClass']

    print('\n로직 처리 순회')

    while  pNode != 0:
        print('{} = {}'.format(count, pNode.data))
        count += 1
        pNode = pNode.pLink['NextClass']

    print('노드 개수 : {}'.format(getLinkedListLength(pList.pLink['HeaderClass'])))

def concatLinkedList(pListA, pListB):
    pNodeA = LinkedListNode()
    pNodeA.LinkedListNode()
    pNodeB = LinkedListNode()
    pNodeB.LinkedListNode()

    if pListA.pLink['HeaderClass'] != 0 and pListB.pLink['HeaderClass'] != 0:
        pNodeA = pListA.pLink['HeaderClass']
        pNodeA = pNodeA.pLink['NextClass']

        pNodeB = pListB.pLink['HeaderClass']
        pNodeB = pNodeB.pLink['NextClass']

        while pNodeA.pLink['NextClass'] != 0 :
            pNodeA = pNodeA.pLink['NextClass']

        pListA.pLink['HeaderClass'].currentCount = getLinkedListLength(pListA.pLink['HeaderClass']) + getLinkedListLength(pListB.pLink['HeaderClass'])
        pNodeA.pLink['NextClass'] = pNodeB.pLink['CurrentClass']
        pListB.pLink.clear()


pList = LinkedListNode()
pList.LinkedListNode()
pList = createLinkedList(pList)
addLinkedListData(pList, 1, 10)
addLinkedListData(pList, 2, 20)
addLinkedListData(pList, 2, 30)

value = getLinkedListData(pList, 1)
print('위치 : {}\n값 : {}\n'.format(1,value))

displayList(pList)

iterateLinkedList(pList)

removeLinkedListData(pList, 1)

iterateLinkedList(pList)
deleteLinkedList(pList)

pListA = LinkedListNode()
pListA.LinkedListNode()
pListA = createLinkedList(pListA)
pListB = LinkedListNode()
pListB.LinkedListNode()
pListB = createLinkedList(pListB)

addLinkedListData(pListA, 1, 10)
addLinkedListData(pListA, 2, 20)
addLinkedListData(pListA, 3, 30)

addLinkedListData(pListB, 1, 40)
addLinkedListData(pListB, 2, 50)

print('연결 리스트 결합 전')
print('\n연결 리스트A')
iterateLinkedList(pListA)
print('\n연결 리스트B')
iterateLinkedList(pListB)

concatLinkedList(pListA, pListB)
print('\n연결 리스트 결합 후')
print('\n연결 리스트A')
iterateLinkedList(pListA)
print('\n연결 리스트B')
iterateLinkedList(pListB)

deleteLinkedList(pListA)
deleteLinkedList(pListB)
