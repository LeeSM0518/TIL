class TermType:
    def TermType(self):
        self.coef = float(0)     # 계수
        self.degree = int(0)     # 차수

class LinkedListNode(TermType):
    def LinkedListNode(self):
        self.Term = TermType()
        self.Term.TermType()
        self.pLink = {'HeaderClass': 0, 'CurrentClass': 0, 'NextClass': 0}
        self.pLink['CurrentClass'] = self
        self.currentCount = 0


def LinkedList(Node):
    Node.pLink['HeaderClass'] = Node
    return Node


def createLinkedList(Node):
    Node = LinkedListNode()
    Node.LinkedListNode()
    LinkedList(Node)
    return Node

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

        pDelNode.Term = None
        pDelNode.pLink.clear()


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

pListA = 0
pListB = 0
pListC = 0

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