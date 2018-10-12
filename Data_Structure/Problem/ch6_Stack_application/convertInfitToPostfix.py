class ExprToken:
    def ExprTokenType(self):
        self.value = float(0)
        self.type = None

class ExprTokenList:
    def ExprTokenList(self):
        self.List = {}
        self.count = 0
    def ExprTokenAdd(self, pValue, pType):
        Node = ExprToken()
        Node.ExprTokenType()
        Node.value = pValue
        Node.type = pType
        self.List[self.count] = Node
        self.count += 1

class LinkedStackNode:
    def LinkedStackNode(self):
        self.data = ExprToken()
        self.data.ExprTokenType()
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

    if pStack != None:
        if pNode != None:
            pNode.Node.data = data
            pNode.Node.BottomClass = pStack.TopClass
            pNode.TopClass = pNode

            pNode.currentCount = pStack.currentCount + 1
            return pNode
    else:
        print('오류, 메모리 할당, pushLS()')


def isLinkedStackEmpty(pStack):
    ret = 0

    if pStack.TopClass != None:
        if pStack.currentCount == 0:
            ret = 1

    return ret  # 빈 리스트면 1리턴


def popLS(pStack):
    data = None
    pReturn = LinkedStack()
    pReturn.LinkedStack()

    if pStack != None:
        if isLinkedStackEmpty(pStack) == 0:
            data = pStack.TopClass.Node.data
            pReturn.TopClass = pStack.Node.BottomClass
            pReturn.Node.BottomClass = pStack.Node.BottomClass.Node.BottomClass
            pStack.currentCount -= 1
            pReturn.currentCount = pStack.currentCount

    return {'data': data, 'pReturn': pReturn}


def deleteLinkedStack(pStack):
    pDelNode = LinkedStackNode()
    pDelNode.LinkedStackNode()
    pNode = LinkedStackNode()
    pNode.LinkedStackNode()

    if pStack != None:
        while pStack != None:
            pStack.TopClass = None
            pStack = pStack.Node.BottomClass
            if pStack == 'HeaderClass':
                pStack = None
                break

    return pStack

def convertInfixToPostfix(pExprTokens) :
    pStack = LinkedStack()
    pStack.LinkedStack()
    pNode = LinkedStackNode()
    pNode.LinkedStackNode()
    tokenType = ExprToken()
    tokenType.ExprTokenType()
    inStackTokenType = ExprToken()
    inStackTokenType.ExprTokenType()
    pData = ExprToken()
    pData.ExprTokenType()

    pStack = createLinkedStack(pStack)
    if pStack != None :
        for i in range(pExprTokens.count) :
            tokenType = pExprTokens.List[i].type
            if tokenType == 'operand' :
                print('%4.1f ' %pExprTokens.List[i].value , end=' ')
            elif tokenType == 'rparen' :
                pop = popLS(pStack)
                pData = pop['data']
                pStack = pop['pReturn']
                while pData != None and pData.type != 'lparen' :
                    printToken(pData)
                    pop = popLS(pStack)
                    pData = pop['data']
                    pStack = pop['pReturn']
                    if pStack.Node.BottomClass == 'HeaderClass' :
                        break
            else :
                while isLinkedStackEmpty(pStack) == 0 :
                    inStackTokenType = pStack.TopClass.Node.data.type
                    if outStackPrecedence(tokenType) <= inStackPrecedence(inStackTokenType) :
                        pop = popLS(pStack)
                        pData = pop['data']
                        pStack = pop['pReturn']
                        if pData != None :
                            printToken(pData)
                    else :
                        break
                pStack = pushLS(pStack, pExprTokens.List[i])
        while isLinkedStackEmpty(pStack) == 0 :
            pop = popLS(pStack)
            pData = pop['data']
            pStack = pop['pReturn']
            if pData != None :
                printToken(pData)

def inStackPrecedence(oper):
    if oper == 'lparen' :
        return int(0)
    elif oper == 'rparen' :
        return int(4)
    elif oper == 'multiply' or oper == 'divide' :
        return int(2)
    elif oper == 'plus' or oper == 'minus' :
        return int(1)
    else :
        print('type 값 오류, inStackPrecedence ')

def outStackPrecedence(oper):
    if oper == 'lparen' :
        return int(5)
    elif oper == 'rparen' :
        return int(4)
    elif oper == 'multiply' or oper == 'divide' :
        return int(2)
    elif oper == 'plus' or open == 'minus' :
        return int(1)
    else :
        print('type 값 오류, outStackPrecedence ')

def printToken(element) :
    if element.type == 'lparen' :
        print('( ', end=' ')
    elif element.type == 'rparen' :
        print(') ', end=' ')
    elif element.type == 'plus' :
        print('+ ', end=' ')
    elif element.type == 'minus' :
        print('- ', end=' ')
    elif element.type == 'multiply' :
        print('* ', end=' ')
    elif element.type == 'divide' :
        print('/ ', end=' ')
    elif element.type == 'operand' :
        print('%4.1f ' %element.value, end=' ')


# main
pExprTokens = ExprTokenList()
pExprTokens.ExprTokenList()
pExprTokens.ExprTokenAdd(2,'operand')
pExprTokens.ExprTokenAdd(0, 'minus')
pExprTokens.ExprTokenAdd(0, 'lparen')
pExprTokens.ExprTokenAdd(3, 'operand')
pExprTokens.ExprTokenAdd(0, 'plus')
pExprTokens.ExprTokenAdd(4, 'operand')
pExprTokens.ExprTokenAdd(0, 'rparen')
pExprTokens.ExprTokenAdd(0, 'multiply')
pExprTokens.ExprTokenAdd(5, 'operand')
print('Infix Expresstion : 2.0 - ( 3.0 + 4.0 ) * 5.0')
print('Postfix Expression : ')
convertInfixToPostfix(pExprTokens)