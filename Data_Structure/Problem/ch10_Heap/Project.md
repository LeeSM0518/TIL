# Data Structure Project



## 1. 더맵게

* **문제설명** : 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 스코빌 지수가 가장 낮은 두 개의 음식을 특별한 방법으로 섞어 새로운 음식을 만든다.

  > 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)



* **입출력 예**

  | scoville             | K    | return |
  | -------------------- | ---- | ------ |
  | [1, 2, 3, 9, 10, 12] | 7    | 2      |

  **ex) 실행 예시**

  1. 스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
     새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5
     가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]
  2. 스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
     새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13
     가진 음식의 스코빌 지수 = [13, 9, 10, 12]

  모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회 이므로 2를 return 한다.

* **코드**

  ```python
  def solution(scoville, k) :
      scoville.sort()		# 스코빌 리스트를 오름차순으로 정렬한다.
      out = 0				# 카운트를 위함
      answer = 0			# 섞은 횟수 저장
      while(True):
          Return = scoville[0] + scoville[1] * 2
          answer += 1	
          scoville[0:2] = []	# 섞은 요소들 제거
          scoville.append(Return)	# 섞은 스코빌 리스트에 추가
          scoville.sort()		
          
          for i in scoville :	# 스코빌 리스트 요소들이 모두 k가 넘엇는지 확인
              if i < k :
                  break
              else :
                  out += 1
          if out == len(scoville): # 요소들이 전부 k가 넘으면 반복문 탈출
              break
          else :
              out = 0
      return answer
  
  sco = [1, 2, 3, 9, 10, 12]
  print(solution(sco, 7))
  ```

  **실행결과**

  ```
  2
  ```

  > 1과 2가 섞여서 sco = [3, 5, 9, 10, 12] 가 되고 answer = 1 이 된다.
  >
  > 3과 5가 섞여서 sco = [9, 10, 12, 13] 가 되고 answer = 2 가 된다.





## 2. 이중우선순위큐

* **문제설명** 

  : 이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조 이다.

  | 명령어 | 수신 탑(높이)         |
  | ------ | --------------------- |
  | I 숫자 | 큐에 주어진 숫자 삽입 |
  | D 1    | 큐에 최댓값 삭제      |
  | D -1   | 큐에 최솟값 삭제      |



* **입출력 예**

  | operations                     | return |
  | ------------------------------ | ------ |
  | ["I 16", "D 1"]                | [0, 0] |
  | ["I 7", "I 5", "I -5", "D -1"] | [7, 5] |

  > ["I 16", "D 1"] 은 16을 삽입하고 다시 16을 제거하여 최대값과 최소값은 0, 0 이 되므로 [0, 0] 이 리턴된다.
  >
  > ["I 7", "I 5", "I -5", "D -1"] 은 7 삽입, 5 삽입 , -5 삽입한 뒤 -5를 제거하여 최대값은 7, 최소값은 5가 되므로 [7, 5] 를 출력시킨다.

* **코드**

  ```python
  def solution(operations):
      op_list = []  # operations를 분할하여 저장할 리스트
      answer = []  # [최대값, 최소값] 을 저장할 리스트
      heap = []  # op 연산을 처리하여 저장할 힙
      
      for i in operations :	# op를 문자와 숫자를 분할하여 저장
          op_list.append(i.split())
      i = 0
      
      while i < len(operations) :	# 입력된 op의 개수만큼 반복
          if op_list[i][0] == 'I' 
              heap.append(int(op_list[i][1]))
          elif op_list[i][0] == 'D' :
              if len(heap) == 0 : # 빈 heap 점검
                  i += 1
                  continue
              elif op_list[i][1] == '1' :
                  heap.pop()
              else :
                  del heap[0]
          heap.sort()
          i += 1
          
      if len(heap) == 0 :
          answer = [0, 0]
      else :
          answer = [heap.pop(), heap[0]]
      return answer
  
  ans = solution(['I 16', 'D 1'])
  print(ans)
  ```

  **실행 결과**

  ```
  [0, 0]
  ```

  > ["I 16", "D 1"] 은 16을 삽입하고 다시 16을 제거하여 최대값과 최소값은 0, 0 이 되므로 [0, 0] 이 리턴된다.

