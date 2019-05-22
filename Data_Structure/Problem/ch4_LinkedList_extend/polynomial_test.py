class TermType:
    def TermType(self):
        self.coef = float(0)     # 계수
        self.degree = int(0)     # 차수
        self.data = 0

class LinkedListNode:
    def LinkedListNode(self):
        self.Term = TermType()
        self.Term.TermType()
        self.data = 0
        self.pLink = {'HeaderClass': 0, 'CurrentClass': 0, 'NextClass': 0}
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

    if position > pList.currentCount:
        print('\n오류, 불러올 위치 값이 현재 노드 개수 보다 많습니다.\n')
        return

    pCurrentNode = LinkedListNode()
    pCurrentNode.LinkedListNode()
    pCurrentNode = pList.pLink['HeaderClass']

    for i in range(position):
        pCurrentNode = pCurrentNode.pLink['NextClass']

    return pCurrentNode.data


def addLinkedListData(pList, position, term):
    i = 0

    if position == 0:
        print('\n오류, 값을 넣을 위치는 헤더노드의 위치입니다.\n')
        return

    pNewNode = LinkedListNode()
    pNewNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pNewNode.term = term

    pPreNode = pList.pLink['HeaderClass']
    for i in range(position - 1):
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pNewNode

    pList.currentCount += 1

    return pList


def removeLinkedListData(pList, position):
    if position == 0:
        print('\n오류, 헤더노드는 지울 수 없습니다.\n')
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
    if pList.pLink == {}:
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pDelNode = LinkedListNode()
    pDelNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pPreNode = pList.pLink['HeaderClass']

    while (pPreNode.pLink['NextClass'] != 0):
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
        print("Node[{}] = {}".format(i + 1, getLinkedListData(pList, i + 1)))


def iterateLinkedList(pList):
    count = int(0)

    if pList.pLink == {}:
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pNode = LinkedListNode()
    pNode.LinkedListNode()

    pNode = pList.pLink['HeaderClass']

    print('\n로직 처리 순회')

    while pNode != 0:
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

        while pNodeA.pLink['NextClass'] != 0:
            pNodeA = pNodeA.pLink['NextClass']

        pListA.pLink['HeaderClass'].currentCount = getLinkedListLength(
            pListA.pLink['HeaderClass']) + getLinkedListLength(pListB.pLink['HeaderClass'])
        pNodeA.pLink['NextClass'] = pNodeB.pLink['CurrentClass']
        pListB.pLink.clear()

    elif pListA.pLink['HeaderClass'] == 0:
        print('오류, A 리스트가 비어있습니다.')
    elif pListB.pLink['HeaderClass'] == 0:
        print('오류, B 리스트가 비어있습니다.')

def addPolyNodeList(pList, coef, degree):
    '''다항식에 새로운 노드를 추가하는 함수 : 다항식에 새로운 항을 추가'''
    ret = 0
    position = 0

    term = TermType()
    term.TermType()
    term.coef = coef
    term.degree = degree

    if pList.pLink['HeaderClass'] != 0 :
        position = pList.currentCount + 1
        ret = addLinkedListData(pList, position, term)

def displayPolyList(pList):
    pNode = LinkedListNode()
    pNode.LinkedListNode()

    pNode = pList.pLink['NextClass']
    if pList.pLink['HeaderClass'] == 0 :
        print('자료가 없습니다.')
        return

    else :
        for i in range(pList.pLink['HeaderClass'].currentCount) :
            if( i > 0):
                print(' + ',end=" ")
            print('%0.1f x^ %d'%(pNode.term.coef,pNode.term.degree),end=" ")
            pNode = pNode.pLink['NextClass']

def polyAdd(pListA, pListB) :
    pReturn = LinkedListNode()
    pReturn.LinkedListNode()

    coefSum = float(0)

    if pListA.pLink['HeaderClass'] != 0 and pListB.pLink['HeaderClass'] != 0 :
        pReturn = createLinkedList(pReturn)
        pNodeA = pListA.pLink['HeaderClass'].pLink['NextClass']
        pNodeB = pListB.pLink['HeaderClass'].pLink['NextClass']

        while (pNodeA != 0 ) and ( pNodeB != 0 ):
            degreeA = pNodeA.term.degree
            degreeB = pNodeB.term.degree
            if degreeA > degreeB :
                coefSum = pNodeA.term.coef
                addPolyNodeList(pReturn, coefSum, degreeA)
                pNodeA = pNodeA.pLink['NextClass']
            elif degreeA == degreeB :
                coefSum = pNodeA.term.coef + pNodeB.term.coef
                addPolyNodeList(pReturn, coefSum, degreeA)
                pNodeA = pNodeA.pLink['NextClass']
                pNodeB = pNodeB.pLink['NextClass']
            else :
                coefSum = pNodeB.term.coef
                addPolyNodeList(pReturn, coefSum, degreeB)
                pNodeB = pNodeB.pLink['NextClass']

        while pNodeA != 0:
            coefSum = pNodeA.term.coef
            addPolyNodeList(pReturn, coefSum, pNodeA.term.degree)
            pNodeA = pNodeA.pLink['NextClass']
        while pNodeB != 0:
            coefSum = pNodeB.term.coef
            addPolyNodeList(pReturn, coefSum, pNodeB.term.degree)
            pNodeB = pNodeB.pLink['NextClass']

    else :
        print('\n오류, NULL 다항식이 전달되었습니다. polyAdd()\n')

    return pReturn

pListA = LinkedListNode()
pListA.LinkedListNode()
pListB = LinkedListNode()
pListB.LinkedListNode()
pListC = LinkedListNode()
pListC.LinkedListNode()

pListA = createLinkedList(pListA)
pListB = createLinkedList(pListB)
pListC = createLinkedList(pListC)

addPolyNodeList(pListA, 7, 6)
addPolyNodeList(pListA, 3, 5)
addPolyNodeList(pListA, 5, 2)
print('A 다항식 = ')
displayPolyList(pListA)

addPolyNodeList(pListB, 1, 5)
addPolyNodeList(pListB, 2, 4)
addPolyNodeList(pListB, 3, 2)
addPolyNodeList(pListB, 4, 0)
print('\nB 다항식 = ')
displayPolyList(pListB)

pListC = polyAdd(pListA, pListB)

print('\nA + B 다항식 = ')
displayPolyList(pListC)
deleteLinkedList(pListC)
deleteLinkedList(pListB)
deleteLinkedList(pListA)