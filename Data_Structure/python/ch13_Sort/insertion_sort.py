import random

list = []
for i in range(0, 20) :
    list.append(random.randrange(1,100))

def insertion_sort(list, n) :
    for i in range(1, n) :
        key = list[i]
        # 키의 이전 위치부터 처음 위치까지 키 값보다 작은 값 탐색
        for j in range(i-1, -1, -1) :
            if list[j] <= key :
                break
            # 현재 위치의 값이 키 값보다 크면 다음 위치의 값에 현재 위치 값 복사
            list[j+1] = list[j]

        if list[j] < key :      # 탐색된 위치의 값이 키 값보다 작으면
            list[j+1] = key
        else:
            list[j] = key

insertion_sort(list, len(list))

print(list)