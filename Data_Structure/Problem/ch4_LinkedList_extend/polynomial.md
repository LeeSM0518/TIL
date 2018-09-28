Data Structure Seminar 
===
*4장 연결리스트 확장*

<br>

# 다항식 연산

* **TermType** : 다항식의 항을 만들어주는 Class
  ```py
  class TermType:
    def TermType(self):
      self.coef = float(0)  # 계수
      self.degree = int(0)  # 차수
  ```

<br>

* **LinkedListNode** : 노드를 만들어주는 Class
  ```py
  class LinkedListNode:
    def LinkedListNode(self):
      self.Term = TermType()    
      self.Term.TermType()      # 노드에 다항식 메서드 실행
      self.pLink = {'HeaderClass':0, 'CurrentClass':0, 'NextClass' : 0}   # 헤더, 현재, 다음 노드의 주소를 저장
      self.pLink['CurrentClass'] = self   # 현재에 현재 노드 주소 저장
      self.currentCount = 0   # 노드 개수 저장
  ```

<br>

* **LinkedList** : 헤더 노드 생성 함수
  ```py
  def LinkedList(Node):
    Node.pLink['HeaderClass'] = Node    # 헤더 주소 위치에 Node 객체를 넣어준다.
    return Node
  ```

<br>

* **createLinkedList** :  리스트를 만들어주는 함수
  ```py
  def createLinkedList(Node):
    Node = LinkedListNode()
    Node.LinkedListNode()   # 노드를 생성하고
    LinkedList(Node)        # 헤더 지정
    return Node
  ```

<br>

* **addLinkedListData** : 리스트에 노드를 추가해주는 함수
  ```py
  def addLinkedListData(pList, position, term):   # 리스트, 위치, 다항식 을 파라미타로 받는다.
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
    for i in range(position - 1):   # 헤더노드에서 부터 노드를 추가시킬 위치 전 까지 링크 이동
        pPreNode = pPreNode.pLink['NextClass']

    pNewNode.pLink['NextClass'] = pPreNode.pLink['NextClass']
    # 새로운 노드의 다음 링크에 추가할 위치 이전의 다음 링크 값을 넣어준다.
    
    pPreNode.pLink['NextClass'] = pNewNode
    # 이전의 다음 링크에 새로운 노드를 넣어준다.

    pList.currentCount += 1

    return pList
    ```

<br>

* **deleteLinkedList** : 리스트를 지우는 함수
  ```py
  def deleteLinkedList(pList):
    if pList.pLink == {}:
        print('\n오류, 리스트가 비어있습니다.\n')
        return

    pDelNode = LinkedListNode()
    pDelNode.LinkedListNode()
    pPreNode = LinkedListNode()
    pPreNode.LinkedListNode()

    pPreNode = pList.pLink['HeaderClass']

    while (pPreNode != 0):   # 노드에 아무것도 없을때까지 반복
        pDelNode = pPreNode   
        pPreNode = pPreNode.pLink['NextClass']  # 지울 노드에 노드를 저장하고 다음 노드로 이동시킨다.

        pDelNode.Term = None
        pDelNode.pLink.clear()  # 데이터와 링크를 지운다.
  ```
  
<br>

* **addPolyNodeList** : 다항식에 새로운 항을 추가하는 함수
  ```py
  def addPolyNodeList(pList, coef, degree):   # 리스트와 계수, 차수를 파라미타로 받는다.
    term = TermType()
    term.TermType()
    term.coef = coef
    term.degree = degree

    if pList.pLink['HeaderClass'] != 0 :    # 리스트가 비어있지 않는다면
        position = pList.currentCount + 1
        addLinkedListData(pList, position, term)  # 노드를 추가시킨다.
  ```

<br>

* **displayPolyList** : 다항식을 출력해주는 함수
  ```py
  def displayPolyList(pList):
    pNode = LinkedListNode()
    pNode.LinkedListNode()

    pNode = pList.pLink['NextClass']
    if pList.pLink['HeaderClass'] == 0 :
        print('자료가 없습니다.')
        return

    else :
        for i in range(pList.pLink['HeaderClass'].currentCount) :   # 현재 가지고 있는 노드의 개수 만큼 다항식 출력
            if( i > 0):
                print(' + ',end=" ")
            print('%0.1f x^ %d'%(pNode.term.coef,pNode.term.degree),end=" ")
            pNode = pNode.pLink['NextClass']
  ```

<br>

* **polyAdd** : 다항식을 더해주는 함수
  ```py
  def polyAdd(pListA, pListB) :
    pReturn = LinkedListNode()
    pReturn.LinkedListNode()

    coefSum = float(0)

    if pListA.pLink['HeaderClass'] != 0 and pListB.pLink['HeaderClass'] != 0 :    # A, B 리스트가 빈 리스트가 아니면
        pReturn = createLinkedList(pReturn)
        pNodeA = pListA.pLink['HeaderClass'].pLink['NextClass']
        pNodeB = pListB.pLink['HeaderClass'].pLink['NextClass']
        # A,B 노드가 서로의 A, B 리스트의 첫 번째 노드를 가리킨다.

        while (pNodeA != 0 ) and ( pNodeB != 0 ): # A,B 노드 둘다 빈 상태가 아닐때
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

        while pNodeA != 0:  # 후처리 작업
            coefSum = pNodeA.term.coef
            addPolyNodeList(pReturn, coefSum, pNodeA.term.degree)
            pNodeA = pNodeA.pLink['NextClass']
        while pNodeB != 0:  # 후처리 작업
            coefSum = pNodeB.term.coef
            addPolyNodeList(pReturn, coefSum, pNodeB.term.degree)
            pNodeB = pNodeB.pLink['NextClass']

    else :
        print('\n오류, NULL 다항식이 전달되었습니다. polyAdd()\n')

    return pReturn
  ```

<br>

* **main**
  ```py
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

  print()

  print(pListC.pLink)
  print(pListB.pLink)
  print(pListA.pLink)
  ```
  **실행결과**
  ```
  A 다항식 = 
  7.0 x^ 6  +  3.0 x^ 5  +  5.0 x^ 2 
  B 다항식 = 
  1.0 x^ 5  +  2.0 x^ 4  +  3.0 x^ 2  +  4.0 x^ 0 
  A + B 다항식 = 
  7.0 x^ 6  +  4.0 x^ 5  +  2.0 x^ 4  +  8.0 x^ 2  +  4.0 x^ 0 
  {}
  {}
  {}
  ```
  