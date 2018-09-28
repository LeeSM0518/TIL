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

* **deleteLinkedList** : 리스트의 노드를 지우는 함수
  ```py
  
