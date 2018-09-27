class DoublyListNode:
    def DoublyListNode(self):
        self.data = 0
        self.pLink = {'HeaderClass':0, 'PreClass':0, 'CurrentClass': 0, 'NextClass':0 }
        self.pLink['CurrentClass'] = self
        self.currentCount = 0

def DoublyList(Node):
    DoublyListNode()
    Node.pLink['HeaderClass'] = Node
    return Node

def createDoublyList(Node):
    Node = DoublyListNode()
    Node.DoublyListNode()
    Node.data = 'HeaderNode'
    Node.pLink['PreClass'] = Node
    Node.pLink['NextClass'] = Node
    DoublyList(Node)
    return Node

def getDoublyListData(pList, position):
    i = 0

    if position > pList.currentCount :
        print('\n오류, 불러올 위치 값이 현재 노드 개수 보다 많습니다.\n')
        return

    pCurrentNode = DoublyListNode()
    pCurrentNode.DoublyListNode()
    pCurrentNode = pList.pLink['HeaderClass']

    for i in range(position):
        pCurrentNode = pCurrentNode.pLink['NextClass']

    return pCurrentNode.data

def addDoublyListData(pList, position, data):
    i = 0

    if position == 0 :
        print('\n오류, 값을 넣을 위치는 헤더노드의 위치입니다.\n')
        return

    pNewNode = DoublyListNode()
    pNewNode.DoublyListNode()
    pPreNode = DoublyListNode()
    pPreNode.DoublyListNode()

    pNewNode.data = data

    pPreNode = pList.pLink['HeaderClass']
    for i in range(position-1) :
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    pNewNode.pLink['PreClass'] = pPreNode

    pPreNode.pLink['NextClass'] = pNewNode
    pNewNode.pLink['NextClass'].pLink['PreClass'] = pNewNode;


    pList.currentCount += 1

    return pList

def removeDoublyListData(pList, position):
    if position == 0 :
        print('\n오류, 헤더노드는 지울 수 없습니다.\n')
        return

    i = int(0)
    pDelNode = DoublyListNode()
    pDelNode.DoublyListNode()
    pPreNode = DoublyListNode()
    pPreNode.DoublyListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in range(position - 1):
        pPreNode = pPreNode.pLink['NextClass']

    pDelNode = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pDelNode.pLink['NextClass']

    pDelNode.pLink['NextClass'].pLink['PreClass'] = pPreNode

    pList.currentCount -= 1

def deleteDoublyList(pList):
    if pList.pLink == {} :
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pDelNode = DoublyListNode()
    pDelNode.DoublyListNode()
    pPreNode = DoublyListNode()
    pPreNode.DoublyListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in range(pList.currentCount - 1):
        pDelNode = pPreNode
        pPreNode = pPreNode.pLink['NextClass']

        pDelNode.data = 0
        pDelNode.pLink.clear()

def getDoublyListLength(pList):
       return pList.pLink['HeaderClass'].currentCount

def displayList(pList):
    i = 0
    print('\n일반 순회')
    for i in range(pList.currentCount):
        print("Node[{}] = {}".format(i+1,getDoublyListData(pList,i+1)))

def iterateDoublyList(pList):
    count = int(0)

    if pList.pLink == {} :
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pNode = DoublyListNode()
    pNode.DoublyListNode()

    pNode = pList.pLink['HeaderClass']

    print('\n로직 처리 순회')

    for i in range(pList.currentCount + 1):
        print('{} = {}'.format(count, pNode.data))
        count += 1
        pNode = pNode.pLink['NextClass']

    print('노드 개수 : {}'.format(getDoublyListLength(pList.pLink['HeaderClass'])))

pList = DoublyListNode()
pList.DoublyListNode()
pList = createDoublyList(pList)
addDoublyListData(pList, 1, 10)
addDoublyListData(pList, 2, 20)
addDoublyListData(pList, 2, 30)

value = getDoublyListData(pList, 1)
print('위치 : {}\n값 : {}\n'.format(1,value))

displayList(pList)

iterateDoublyList(pList)

removeDoublyListData(pList, 1)

iterateDoublyList(pList)
deleteDoublyList(pList)