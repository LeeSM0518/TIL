# Data Structure

### 5장 Stack



## LinkedStack.py  # 연결 스택



* **LinkedStackNode** : 노드 타입

  ```python
  class LinkedStackNode:
      def LinkedStackNode(self):
          self.data = None			# 자료 부분
          self.BottomClass = None		# 연결 부분
  ```


* **LinkedStack** : 스택 타입

  ```python
  class LinkedStack:
      def LinkedStack(self):
          self.Node = LinkedStackNode()
          self.Node.LinkedStackNode()		# 노드 메서드 실행
          self.currentCount = 0			# 현재 위치 번호 및 개수
          self.TopClass = None			# 탑노드 위치
  ```



* **createLinkedStack** : 스택 생성

  ```python
  def createLinkedStack(pStack):
      pStack = LinkedStack()
      pStack.LinkedStack()		# 스택 메서드 실행
      pStack.TopClass = pStack	# Top 위치에 새로운 Stack 저장
      pStack.Node.BottomClass = 'HeaderClass'		
      pStack.Node.data = 'HeaderClass'			# 가장 아래의 노드에 헤더노드 저장
      return pStack
  ```



* **pushLS**	: 새로운 노드 생성

  ```python
  def pushLS(pStack, data):
      pNode = LinkedStack()
      pNode.LinkedStack()
      
      if pStack != None :			# 스택이 생성되어있는지 없는지 확인
          if pNode != None :		# 임의로 만들어준 스택이 잘 만들어졌는지 확인
              pNode.Node.data = data
              pNode.Node.BottomClass = pStack.TopClass	# 새로운 스택에 기존 스택을 연결
              pNode.TopClass = pNode	# 새로운 노드가 Top 이 된다.
  
              pNode.currentCount = pStack.currentCount + 1	# 스택의 개수 증가
              return pNode
      else:
          print('오류, 메모리 할당, pushLS()')
  ```



* **isLinkedStackEmpty** : 스택이 비어있는지 확인

  ```python
  def isLinkedStackEmpty(pStack):
      ret = 0
  
      if pStack.TopClass != None :		# Top이 비어있지 않고
          if pStack.currentCount == 0 :	# 현재 개수가 0일때
              ret = 1
  
      return ret  # 빈 리스트면 1리턴
  ```



* **popLS** : Top을 하나 빼는 함수

  ```python
  def popLS(pStack):
      data = None	
      pReturn = LinkedStack()
      pReturn.LinkedStack()
  
      if pStack != None :
          if isLinkedStackEmpty(pStack) == 0 :
              data = pStack.TopClass.Node.data	# 데이터 값에 탑 데이터를 넣어준다.
              pReturn.TopClass = pStack.Node.BottomClass	
              # 스택의 탑을 pop으로 빠지는 값 밑의 값으로 지정
              
              pReturn.Node.BottomClass = pStack.Node.BottomClass.Node.BottomClass
              # 지워진 후의 Top 스택의 밑의 값은 지워질 탑 위치에서 이전의 이전값으로 지정
              pStack.currentCount -= 1
              pReturn.currentCount = pStack.currentCount
  
      return {'data' : data, 'pReturn' : pReturn}		
  	# dict 형태로 data와 스택을 같이 반환
  ```



* **deleteLinkedStack** : 연결 스택 리스트를 삭제

  ```python
  def deleteLinkedStack(pStack):
      pDelNode = LinkedStackNode()
      pDelNode.LinkedStackNode()
      pNode = LinkedStackNode()
      pNode.LinkedStackNode()
  
      if pStack != None :
          while pStack != None :		# 스택이 없을 때 까지
              pStack.TopClass = None
              pStack = pStack.Node.BottomClass
              if pStack == 'HeaderClass' :	# 스택이 맨아래인 Header를 만났을 때
                  pStack = None
                  break
  
      return pStack
  ```


* **displayLinkedStack** : 연결 스택 리스트 출력

  ```python
  def displayLinkedStack(pStack) :
      i = 0
  
      if pStack != None :
          print('현재 노드 개수 : ', pStack.currentCount)
  
          while pStack != 'HeaderClass' :		# 맨아래 스택인 HeaderClass를 만날때까지
              print('[{}] - [{}]'.format(pStack.currentCount , pStack.TopClass.Node.data))
              pStack = pStack.Node.BottomClass
      print()
  ```


* **main 함수**

  ```python
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
  ```


### 실행결과

```
현재 노드 개수 :  4
[4] - [D]
[3] - [C]
[2] - [B]
[1] - [A]
[0] - [HeaderClass]

pop - [D]

현재 노드 개수 :  3
[3] - [C]
[2] - [B]
[1] - [A]
[0] - [HeaderClass]

peek - [C]

현재 노드 개수 :  3
[3] - [C]
[2] - [B]
[1] - [A]
[0] - [HeaderClass]

pStack =  None
```

