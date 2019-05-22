# Data Structure

### 스택 ( 중위 표현식에서 후위 표현식으로 변환)

* **ExprTokenType** : Token Class

  ```python
  class ExprToken:
      def ExprTokenType(self):
          self.value = float(0)	# 피연산자
          self.type = None		# 연산자
  ```



* **ExprTokenList** :  Token 리스트 및 Token 저장 Class

  ```python
  class ExprTokenList:
      def ExprTokenList(self):	# 리스트 및 토큰 개수 선언
          self.List = {}
          self.count = 0
      def ExprTokenAdd(self, pValue, pType):	# 토큰을 리스트에 저장
          Node = ExprToken()
          Node.ExprTokenType()
          Node.value = pValue
          Node.type = pType
          self.List[self.count] = Node
          self.count += 1
  ```



* **LinkedStackNode** :  Stack 노드 Class

  ```python
  class LinkedStackNode:
      def LinkedStackNode(self):		# Stack 노드 선언
          self.data = ExprToken()
          self.data.ExprTokenType()
          self.BottomClass = None
  ```



* **LinkedStack** :  Stack Class

  ```python
  class LinkedStack:
      def LinkedStack(self):				# Stack 선언
          self.Node = LinkedStackNode()
          self.Node.LinkedStackNode()
          self.currentCount = 0
          self.TopClass = None
  ```



* **createLinkedStack** :  Stack 생성 함수

  ```python
  def createLinkedStack(pStack):		# 헤더를 지정하며 Stack 생성
      pStack = LinkedStack()
      pStack.LinkedStack()
      pStack.TopClass = pStack
      pStack.Node.BottomClass = 'HeaderClass'
      pStack.Node.data = 'HeaderClass'
      return pStack
  ```



* **pushLS** :  Stack 에 노드 추가 함수

  ```python
  def pushLS(pStack, data):
      pNode = LinkedStack()
      pNode.LinkedStack()
  
      if pStack != None:
          if pNode != None:
              pNode.Node.data = data
              
              # Stack 의 Top을 새로운 노드의 아래에 연결
              pNode.Node.BottomClass = pStack.TopClass	
              pNode.TopClass = pNode	# 새로운 노드를 Top 으로 지정
              pNode.currentCount = pStack.currentCount + 1
              return pNode
      else:
          print('오류, 메모리 할당, pushLS()')
  ```



* **isLinkedStackEmpty** :  빈 Stack 인지 확인 함수

  ```python
  def isLinkedStackEmpty(pStack):
      ret = 0
  
      if pStack.TopClass != None:
          if pStack.currentCount == 0:
              ret = 1
  
      return ret  # 빈 리스트면 1리턴
  ```



* **popLS** :  Stack 에서 노드를 가져오는 함수

  ```python
  def popLS(pStack):
      data = None
      pReturn = LinkedStack()
      pReturn.LinkedStack()
  
      if pStack != None:
          if isLinkedStackEmpty(pStack) == 0:
              data = pStack.TopClass.Node.data
              
              # 새로운 Stack의 Top을 이전 Stack의 Bottom으로 지정
              pReturn.TopClass = pStack.Node.BottomClass
              # 새로운 Stack의 Bottom을 이전 Stack의 Bottom의 Bottom으로 연결
              pReturn.Node.BottomClass = pStack.Node.BottomClass.Node.BottomClass
              pStack.currentCount -= 1
              pReturn.currentCount = pStack.currentCount
  
      return {'data': data, 'pReturn': pReturn}	# 데이터와 스택을 따로 반환
  ```




* **deleteLinkedStack** :  Stack 삭제

  ```python
  def deleteLinkedStack(pStack):
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
  ```



* **convetInfixToPostfix** :  중위 표현식에서 후위 표현식으로 변환 함수

  ```python
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
          # 토큰의 개수 만큼 반복문을 실행하여 수식을 변환한다.
          for i in range(pExprTokens.count) :
              tokenType = pExprTokens.List[i].type
              if tokenType == 'operand' :	# 피연산자를 만나면 바로 출력
                  print('%4.1f ' %pExprTokens.List[i].value , end=' ')
              elif tokenType == 'rparen' :# 닫는 괄호 연산자를 만난 경우
                  pop = popLS(pStack)
                  pData = pop['data']
                  pStack = pop['pReturn']
                  # 여는 괄호를 만날 때 까지 반복문을 실행하면서 스택 내 연산자들을 팝하고 출력
                  while pData != None and pData.type != 'lparen' :
                      printToken(pData)
                      pop = popLS(pStack)
                      pData = pop['data']
                      pStack = pop['pReturn']
                      if pStack.Node.BottomClass == 'HeaderClass' :
                          break
              else :	# 피연산자와 닫는 괄호 연산자 이외의 경우
                  while isLinkedStackEmpty(pStack) == 0 :
                      inStackTokenType = pStack.TopClass.Node.data.type
                      # 스택 내에 있는 연산자 와 현재 수식에서 읽은 연산자ㅇ,; 우선 순위를 비교한다.
                      if outStackPrecedence(tokenType) <= inStackPrecedence(inStackTokenType) :
                          pop = popLS(pStack)
                          pData = pop['data']
                          pStack = pop['pReturn']
                          
                          # 비교한 결과가 스택의 연산자 중에서 현재 수식에서 읽은 연산자보다 우선 순위가 높은 연산자들은 모두 pop하고 push한다.
                          if pData != None :
                              printToken(pData)
                      else :
                          break
                  pStack = pushLS(pStack, pExprTokens.List[i])
          # 스택에 남은 연산자들을 팝하여 출력한다
          while isLinkedStackEmpty(pStack) == 0 :
              pop = popLS(pStack)
              pData = pop['data']
              pStack = pop['pReturn']
              if pData != None :
                  printToken(pData)
  ```



* **isStackPrecedence** :  스택 내부에서의 우선순위를 반환하는 함수

  ```python
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
  ```


* **outStackPrecedentce** :  스택 외부에서의 우선순위를 반환하는 함수

  ```python
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
  ```



* **printToken** :  토큰의 내용을 출력하는 함수

  ```python
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
  ```



* **main 함수**

  ```python
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
  ```


* **실행결과**

  ```
  Infix Expresstion : 2.0 - ( 3.0 + 4.0 ) * 5.0
  Postfix Expression : 
   2.0   3.0   4.0  +   5.0  *  -  
  ```
