# Data Structure Project

## DFS & BFS

### 네트워크

* **문제 설명** : 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

* **입출력 예** 

  ![1543313712411](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543313712411.png)



* **입출력 예 설명**

  * **예제 1** : 2개의 네트워크가 존재

  ![1543313745806](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543313745806.png)

  * **예제 2** : 1개의 네트워크가 존재



* **코드**

  ```python
  def solution(n, computers):
      answer = n
      i = 0
      j = 0
      visited = []	# 방문한 노드 저장
      if n >= 1 and n <= 200 :
          while True :
              if i == n:      # 전부 다 확인시
                  break
              elif j == n:    # 한 노드에 대한 간선을 다 확인시
                  i += 1
                  j = 0
              elif i == j :
                  j += 1
              elif computers[i][j] == 1 : # 간선이 존재할 경우
                  if visited == [] :  	# 방문한 노드가 없을 경우
                      visited.append(i)
                      visited.append(j)
                      j += 1
                      answer -= 1
                  elif visited.count(j) > 0: # 방문한 노드일 경우
                      j += 1
                  else :  				   # 방문하지 않은 노드일 경우
                      visited.append(j)
                      answer -= 1
                      j += 1
              else :
                  j+= 1
      return answer
  ```



### 여행경로

* **문제 설명** : 주어진 항공권을 모두 이용하여 여행경로를 짠다. 항상 "ICN" 공항에서 출발한다.



* **입출력 예**

  ![1543314307666](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1543314307666.png)

​	

* **코드**

  ```python
  class Heap :
      def __init__(self):
          self.heap_data = [None]
  
      def insert_heap(self, value):	# 노드 삽입 (최소힙)
          i = 0
          if self != None :
              # 빈 힙이 아닐 경우
              if len(self.heap_data) != 1 :
                  i = len(self.heap_data)
                  # 부모 노드보다 삽입할 노드가 작을 경우 반복
                  while i != 1 and value < self.heap_data[int(i/2)] :
                      i = int(i/2)
                  self.heap_data.insert(i, value)
              # 빈 힙일 경우
              else :
                  self.heap_data.append(value)
  
  def solution(tickets) :
      answer = []
      icn = Heap()
      others = Heap()
      destination = None
      for values in tickets :		# ICN은 항상 출발지 이므로 따로 힙 저장
          if values[0] == 'ICN' :
              icn.insert_heap(values)
          else :
              others.insert_heap(values)
      icn.heap_data.remove(None)
      others.heap_data.remove(None)
      while True :
          if icn.heap_data == [] and others.heap_data == [] :
              break
          elif answer == [] :		# 처음 출발했을 때
              start = icn.heap_data[0]		# ICN의 첫 원소를 가져옴
              icn.heap_data.remove(start)		# ICN의 첫 원소를 지움
              destination = start[1]			# 도착지 저장
              answer.append(start[0])			# 출발지 answer에 저장
              
          elif destination == 'ICN':	# 도착지가 ICN 일때
              start = icn.heap_data[0]	# icn 힙에서 첫번째 데이터를 가져온다
              icn.heap_data.remove(start)	# 가져온 데이터를 힙에서 지운다
              destination = start[1]		# 도착지 저장
              answer.append(start[0])		# 출발지 answer에 저장
              
          for route in others.heap_data :	# 출발지가 ICN이 아닌 나머지 경로들
              if route != None :
                  if route[0] == destination :	# 이전에 저장해둔 도착지와 출발지가 일치
                      answer.append(route[0])		# 출발지 answer에 저장
                      destination = route[1]		# 도착지 저장
                      others.heap_data.remove(route)	# 사용한 route 제거
      answer.append(destination)	# 마지막 도착지 저장
      return answer
  ```





## 이분탐색



### 예산

* **문제 설명** : 국가예산을 여러 지방의 예산요청을 심사하여 국가의 예산을 분배하는 것입니다. 하지만 국가예산의 총액은 미리 정해져 있어서 정해진 총액 이하에서 **가능한 한 최대의** 총 예산을 다음과 같은 방법으로 배정합니다.

  > 1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
  > 2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정한다. 상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다.



* **입출력 예**

  **budgets** = 지방의 예산요청

  **M** = 국가의 총 예산

  **return** = 조건을 모두 만족하는 상한액

  | budgets              | M    | return |
  | -------------------- | ---- | ------ |
  | [120, 110, 140, 150] | 485  | 127    |



* **입출력 예 설명**

  : 상한액을 127로 잡으면 위의 요청들에 대해서 각각 120, 110, 127, 127을 배정하고 그것이 484로 가능한 최대가 된다.



* **코드**

  ```python
  def solution(budgets, M):
      answer = 0
      budgets.sort()					# 예산 정렬
      cut_line = M / len(budgets)		# 상한액을 구하기 위한 커트라인 계산
      for i in range(len(budgets)) :	# 커트라인을 넘는 위치 검색
          if budgets[i] > cut_line :
              break
      budget_excess = len(budgets) - i	# 커트라인을 초과하는 예산 개수
      
      # 상한액 구하는 과정
      for j in range(i) :
          M -= budgets[j]					# 총 예산에서 커트라인을 넘지 않는 예산들 차감
      answer = int(M / budget_excess)		# 상한액 계산
      
      # 상한액을 넘는 예산들 조정
      for i in range(budget_excess, len(budgets)) :	
          budgets[i] = answer
          
      return answer
  ```




### 입국심사

* **문제 설명** : n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다. 모든 사람이 심사를 받는데 걸리는 시간을 최소로 구해보자.


* **입출력 예**

| n    | times   | return |
| ---- | ------- | ------ |
| 6    | [7, 10] | 28     |



* **입출력 예 설명**

  1. 가장 첫 두 사람은 바로 심사를 받으러 갑니다.

  2. 7분이 되었을 때, 첫 번째 심사대가 비고 3번째 사람이 심사를 받습니다.

  3. 10분이 되었을 때, 두 번째 심사대가 비고 4번째 사람이 심사를 받습니다.

  4. 14분이 되었을 때, 첫 번째 심사대가 비고 5번째 사람이 심사를 받습니다.

  5. 20분이 되었을 때, 두 번째 심사대가 비지만 6번째 사람이 그곳에서 심사를 받지 않고 1분을 더 기다린 후에 첫 번째 심사대에서 심사를 받으면 28분에 모든 사람의 심사가 끝납니다.



* **코드**

  ```python
  import Heap
  
  def solution(n, times):
      answer = 0
      i = 0
      heap = Heap.Heap()
      for value in times :					# 시간들을 하나씩 value에 저장
          for i in range(1, n) :				# n-1 번 까지
              heap.insert_heap(value * i)		# value * i 한 값을 힙에 삽입
      for i in range(n - 1) :					# n-2 번 까지
          heap.remove_heap()					# heap의 최소를 삭제시킨다.
      answer = heap.heap_data[1]				
      return answer
  
  n = 6
  times = [7, 10]
  print(solution(n, times))
  ```
