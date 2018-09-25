class LinkedListNode():
    data = int(0)
    pLink = {'HeaderClass':0, 'CurrentClass': 0, 'NextClass':0 }

class LinkedList():
    currentCount = int(0)
    Node = LinkedListNode()
    Node.pLink['HeaderClass'] = Node

def creatLinkedList(self):
    self = LinkedList()
    self.pLink['CurrentClass'] = self

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

def concatLinkedList(pListA, pListB):




