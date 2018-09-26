class LinkedListNode():
    data = int(0)
    pLink = {'HeaderClass':0, 'CurrentClass': 0, 'NextClass':0 }

class LinkedList():
    currentCount = int(0)
    self = LinkedListNode()
    self.pLink['HeaderClass'] = self

class createLinkedList():
    def createLinked(self):
        self = LinkedList()
        self.pLink['CurrentClass'] = self
        print(self.pLink)

def getLinkedListData(pList, position):
    i = 0
    pCurrentNode = LinkedList()
    for i in position:
        pCurrentNode = pCurrentNode.pLink['NextClass']

    return pCurrentNode.data

def addLinkedListData(pList, position, data):
    i = 0
    pNewNode = LinkedListNode()
    pPreNode = LinkedListNode()

    pNewNode.data = data

    pPreNode = pList.pLink['HeaderClass']
    for i in position - 1 :
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pNewNode.pLink['CurrentClass']
    pList.currentCount += 1

def removeLinkedListData(pList, position):
    i = int(0)
    pDelNode = LinkedListNode()
    pPreNode = LinkedListNode()

    pPreNode = pList.pLink['HeaderClass']

    for i in position - 1:
        pPreNode = pPreNode.pLink['NextClass']

    pDelNode = pPreNode.pLink['NextClass']
    pPreNode.pLink['NextClass'] = pDelNode.pLink['NextClass']

    pList.currentCount -= 1

def deleteLinkedList(pList):
    pDelNode = LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode = pList.pLink['headerClass']

    while(pPreNode.pLink['NextNode'] != 0 ):
        pDelNode = pPreNode
        pPreNode = pPreNode.pLink['NextClass']

        pDelNode.data = 0
        pDelNode.pLink.clear()

def getLinkedListLength(pList):
    if 0 != pList.pLink['NextClass']:
       return pList.currentCount

def displayList(pList):
    i = int(0)
    print('\n일반 순회')
    for i in pList.currentCount - 1 :
        print("{} = {}".format(i,getLinkedListData(pList,i)))

def iterateLinkedList(pList):
    count = int(0)
    pNode = LinkedListNode

    pNode = pList.pLink['HeaderClass']

    print('로직 처리 순회')
    while(pNode.pLink['HeaderClass']):
        print('{} = {}'.format(count, pNode.data))
        count += 1
        pNode = pNode.pLink['NextClass']

    print('노드 개수 : {}'.format(count))

def concatLinkedList(pListA, pListB):
    pNodeA = LinkedListNode()
    pNodeB = LinkedListNode()

    if pListA.pLink['HeaderClass'] != 0 and pListB.pLink['HeaderClass'] != 0:
        pNodeA = pListA.pLink['HeaderClass']
        pNodeA = pNodeA.pLink['NextClass']

        pNodeB = pListB.pLink['HeaderClass']
        pNodeB = pNodeB.pLink['NextClass']

        while pNodeA.pLink['HeaderClass'] and pNodeA.pLink['NextClass'] :
            pNodeA = pNodeA.pLink['NextClass']

        pNodeA.pLink['NextClass'] = pNodeB.pLink['CurrentClass']
        pListB.pLink.clear()

pList = createLinkedList()
addLinkedListData(pList, 0, 10)
addLinkedListData(pList, 1, 20)
addLinkedListData(pList, 1, 30)

value = getLinkedListData(pList, 1)
print('위치 : {}\n값 : {}\n'.format(1,value))

displayList(pList)

iterateLinkedList(pList)

removeLinkedListData(pList, 0)
deleteLinkedList(pList)

pListA = createLinkedList()
pListB = createLinkedList()

addLinkedListData(pListA, 0, 10)
addLinkedListData(pListA, 1, 20)
addLinkedListData(pListA, 2, 30)

addLinkedListData(pListB, 0, 40)
addLinkedListData(pListB, 1, 50)

print('연결 리스트 결합 전')
print('\n연결 리스트A\n')
iterateLinkedList(pListA)
print('\n연결 리스트B\n')
iterateLinkedList(pListB)

concatLinkedList(pListA, pListB)
print('\n연결 리스트 결합 후\n')
print('\n연결 리스트A\n')
iterateLinkedList(pListA)
print('\n연결 리스트B\n')
iterateLinkedList(pListB)

deleteLinkedList(pListA)
deleteLinkedList(pListB)



