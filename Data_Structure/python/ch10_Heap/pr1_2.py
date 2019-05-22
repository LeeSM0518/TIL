import heap_2

def solution(scoville, k):
    answer = 0
    out = 0
    Return = 0
    pHeap = heap_2.Heap()
    delt = 0
    for i in scoville :
        pHeap.insert_heap(i)
    while(True):
        print(pHeap.heap_data)
        Return = pHeap.heap_data[1] + 2 * pHeap.heap_data[2]
        answer += 1
        pHeap.remove_heap()
        pHeap.remove_heap()
        pHeap.insert_heap(Return)
        for i in range(1, len(pHeap.heap_data)) :
            if pHeap.heap_data[i] < k :
                break
            else :
                out += 1
        if out == len(pHeap.heap_data) - 1 :
            break
        else:
            out = 0
    return answer

sco = [1,2,3,9,10,12]
print(solution(sco, 7))

