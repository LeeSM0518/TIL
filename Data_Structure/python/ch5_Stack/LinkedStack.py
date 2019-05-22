class LinkedStackNode:
    def LinkedStackNode(self):
        self.data = None
        self.BottomClass = None

class LinkedStack:
    def LinkedStack(self):
        self.Node = LinkedStackNode()
        self.Node.LinkedStackNode()
        self.currentCount = 0
        self.TopClass = None

def createLinkedStack(pStack):
    pStack = LinkedStack()
    pStack.LinkedStack()
    pStack.TopClass = pStack
    pStack.Node.BottomClass = 'HeaderClass'
    pStack.Node.data = 'HeaderClass'
    return pStack

def pushLS(pStack, data):
    pNode = LinkedStack()
    pNode.LinkedStack()
    
    if pStack != None :
        if pNode != None :
            pNode.Node.data = data
            pNode.Node.BottomClass = pStack.TopClass
            pNode.TopClass = pNode

            pNode.currentCount = pStack.currentCount + 1
            return pNode
    else:
        print('오류, 메모리 할당, pushLS()')

def isLinkedStackEmpty(pStack):
    ret = 0

    if pStack.TopClass != None :
        if pStack.currentCount == 0 :
            ret = 1

    return ret  # 빈 리스트면 1리턴

def popLS(pStack):
    data = None
    pReturn = LinkedStack()
    pReturn.LinkedStack()

    if pStack != None :
        if isLinkedStackEmpty(pStack) == 0 :
            data = pStack.TopClass.Node.data
            pReturn.TopClass = pStack.Node.BottomClass
            pReturn.Node.BottomClass = pStack.Node.BottomClass.Node.BottomClass
            pStack.currentCount -= 1
            pReturn.currentCount = pStack.currentCount

    return {'data' : data, 'pReturn' : pReturn}

def deleteLinkedStack(pStack):
    pDelNode = LinkedStackNode()
    pDelNode.LinkedStackNode()
    pNode = LinkedStackNode()
    pNode.LinkedStackNode()

    if pStack != None :
        while pStack != None :
            pStack.TopClass = None
            pStack = pStack.Node.BottomClass
            if pStack == 'HeaderClass' :
                pStack = None
                break

    return pStack

def displayLinkedStack(pStack) :
    i = 0

    if pStack != None :
        print('현재 노드 개수 : ', pStack.currentCount)

        while pStack != 'HeaderClass' :
            print('[{}] - [{}]'.format(pStack.currentCount , pStack.TopClass.Node.data))
            pStack = pStack.Node.BottomClass
    print()

# main
pStack = 0
pStack = createLinkedStack(pStack)

if pStack != None :
    pStack = pushLS(pStack, 'A')
    pStack = pushLS(pStack, 'B')
    pStack = pushLS(pStack, 'C')
    pStack = pushLS(pStack, 'D')
    displayLinkedStack(pStack)

    pop = popLS(pStack)
    data = pop['data']
    pStack = pop['pReturn']

    print('pop - [{}]\n'.format(data))

    displayLinkedStack(pStack)

    print('peek - [{}]\n'.format(pStack.TopClass.Node.data))

    displayLinkedStack(pStack)

    pStack = deleteLinkedStack(pStack)

print('pStack = ',pStack)
