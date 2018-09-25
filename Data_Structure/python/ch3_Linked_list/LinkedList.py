class LinkedListNode():
    data = int(0)
    pLink = {'CurrentClass': 0, 'NextClass':0 }

class LinkedList():
    currentCount = int(0)
    headerNode = LinkedListNode()

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

    pPreNode = pList.headerNode
    for i in position - 1 :
        pPreNode = pPreNode.pLink['NextClass']
