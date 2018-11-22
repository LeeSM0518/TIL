import heap_2
import heap_2_max

def solution(operations):
    op_list = []  # operations를 분할하여 저장할 리스트
    answer = []  # [최대값, 최소값] 을 저장할 리스트
    count = 0
    heap_min = heap_2.Heap()  # op 연산을 처리하여 저장할 힙
    heap_max = heap_2_max.Heap()
    for i in operations :
        op_list.append(i.split())
    i = 0
    while i < len(operations) :
        if op_list[i][0] == 'I' :
            heap_min.insert_heap(int(op_list[i][1]))
            heap_max.insert_heap(int(op_list[i][1]))
            count += 1
        elif op_list[i][0] == 'D' :
            if len(heap_min.heap_data) == 1 :
                i += 1
                continue
            elif op_list[i][1] == '1' :
                heap_max.remove_heap()
                count -= 1
            else :
                heap_min.remove_heap()
                count -= 1
        i += 1
    if count == 0 :
        answer = [0, 0]
    else :
        answer = [heap_2_max.remove_heap(), heap_2.remove_heap()]
    return answer

ans = solution(['I 16', 'D 1'])
print(ans)