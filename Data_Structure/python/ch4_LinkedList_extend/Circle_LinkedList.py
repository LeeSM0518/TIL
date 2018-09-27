class CircularListNode:
    def CircularListNode(self):
        self.data = 0
        self.pLink = {'HeaderClass': 0, 'CurrentClass': 0, 'NextClass': 0}
        self.pLink['CurrentClass'] = self
        self.currentCount = 0


def CircularList(Node):
    CircularListNode()
    Node.pLink['HeaderClass'] = Node
    return Node


def createCircularList(Node):
    Node = CircularListNode()
    Node.CircularListNode()
    Node.data = 'HeaderNode'
    CircularList(Node)
    return Node


def getCircularListData(pList, position):
    i = 0

    if position > pList.currentCount:
        print('\n오류, 불러올 위치 값이 현재 노드 개수 보다 많습니다.\n')
        return

    pCurrentNode = CircularListNode()
    pCurrentNode.CircularListNode()
    pCurrentNode = pList.pLink['HeaderClass']

    for i in range(position):
        pCurrentNode = pCurrentNode.pLink['NextClass']

    return pCurrentNode.data


def addCircularListData(pList, position, data):
    i = 0

    if position == 0:
        print('\n오류, 값을 넣을 위치는 헤더노드의 위치입니다.\n')
        return

    pNewNode = CircularListNode()
    pNewNode.CircularListNode()
    pPreNode = CircularListNode()
    pPreNode.CircularListNode()

    pNewNode.data = data

    pPreNode = pList.pLink['HeaderClass']
    for i in range(position - 1):
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pNewNode

    pList.currentCount += 1

    if pNewNode.pLink['NextClass'] == 0:
        pNewNode.pLink['NextClass'] = pNewNode;

    return pList


def removeCircularListData(pList, position):
    if position == 0:
        print('\n오류, 헤더노드는 지울 수 없습니다.\n')
        return

    i = int(0)
    pDelNode = CircularListNode()
    pDelNode.CircularListNode()
    pPreNode = CircularListNode()
    pPreNode.CircularListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in range(position - 1):
        pPreNode = pPreNode.pLink['NextClass']

    pDelNode = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pDelNode.pLink['NextClass']

    pList.currentCount -= 1


def deleteCircularList(pList):
    if pList.pLink == {}:
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pDelNode = CircularListNode()
    pDelNode.CircularListNode()
    pPreNode = CircularListNode()
    pPreNode.CircularListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in range(pList.currentCount - 1):
        pDelNode = pPreNode
        pPreNode = pPreNode.pLink['NextClass']

        pDelNode.data = 0
        pDelNode.pLink.clear()


def getCircularListLength(pList):
    return pList.pLink['HeaderClass'].currentCount


def displayList(pList):
    i = 0
    print('\n일반 순회')
    for i in range(pList.currentCount):
        print("Node[{}] = {}".format(i + 1, getCircularListData(pList, i + 1)))


def iterateCircularList(pList):
    count = int(0)

    if pList.pLink == {}:
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pNode = CircularListNode()
    pNode.CircularListNode()

    pNode = pList.pLink['HeaderClass']

    print('\n로직 처리 순회')

    for i in range(pList.currentCount + 1):
        print('Node[{}] = {}'.format(count, pNode.data))
        count += 1
        pNode = pNode.pLink['NextClass']

    print('노드 개수 : {}'.format(getCircularListLength(pList.pLink['HeaderClass'])))


def concatCircularList(pListA, pListB):
    pNodeA = CircularListNode()
    pNodeA.CircularListNode()
    pNodeB = CircularListNode()
    pNodeB.CircularListNode()

    if pListA.pLink['HeaderClass'] != 0 and pListB.pLink['HeaderClass'] != 0:
        pNodeA = pListA.pLink['HeaderClass']
        pNodeA = pNodeA.pLink['NextClass']

        pNodeB = pListB.pLink['HeaderClass']
        pNodeB = pNodeB.pLink['NextClass']

        for i in range(pListA.currentCount - 1):
            pNodeA = pNodeA.pLink['NextClass']

        pListA.pLink['HeaderClass'].currentCount = getCircularListLength(
            pListA.pLink['HeaderClass']) + getCircularListLength(pListB.pLink['HeaderClass'])
        pNodeA.pLink['NextClass'] = pNodeB.pLink['CurrentClass']
        pListB.pLink.clear()

    elif pListA.pLink['HeaderClass'] == 0:
        print('오류, A 리스트가 비어있습니다.')
    elif pListB.pLink['HeaderClass'] == 0:
        print('오류, B 리스트가 비어있습니다.')


pList = CircularListNode()
pList.CircularListNode()
pList = createCircularList(pList)
addCircularListData(pList, 1, 10)
addCircularListData(pList, 2, 20)
addCircularListData(pList, 2, 30)

value = getCircularListData(pList, 1)
print('위치 : {}\n값 : {}\n'.format(1, value))

displayList(pList)

iterateCircularList(pList)

removeCircularListData(pList, 1)

iterateCircularList(pList)
deleteCircularList(pList)

pListA = CircularListNode()
pListA.CircularListNode()
pListA = createCircularList(pListA)
pListB = CircularListNode()
pListB.CircularListNode()
pListB = createCircularList(pListB)

addCircularListData(pListA, 1, 10)
addCircularListData(pListA, 2, 20)
addCircularListData(pListA, 3, 30)

addCircularListData(pListB, 1, 40)
addCircularListData(pListB, 2, 50)

print('연결 리스트 결합 전')
print('\n연결 리스트A')
iterateCircularList(pListA)
print('\n연결 리스트B')
iterateCircularList(pListB)

concatCircularList(pListA, pListB)
print('\n연결 리스트 결합 후')
print('\n연결 리스트A')
iterateCircularList(pListA)
print('\n연결 리스트B')
iterateCircularList(pListB)

deleteCircularList(pListA)
deleteCircularList(pListB)
