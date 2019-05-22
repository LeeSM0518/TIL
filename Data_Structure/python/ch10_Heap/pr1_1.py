import heap

# A = heap.HeapType()
def solution(scoville, k) :
    answer = 0
    out = 0
    pHeap = heap.HeapType()
    pHeap.createArrayMaxHeap(len(scoville)+1)
    for i in scoville :
        pHeap.insertMaxHeapAH(i)
    while(True):
        print(pHeap.pData)
        Return = pHeap.pData[1] + pHeap.pData[2] * 2
        answer += 1
        pHeap.removeMaxHeapAH()
        pHeap.removeMaxHeapAH()
        pHeap.insertMaxHeapAH(Return)
        for i in range(1, pHeap.currentCount) :
            if pHeap.pData[i] < k:
                break
            else :
                out += 1
        if out == len(scoville):
            break
        else:
            out = 0
    return answer

sco = [1, 2, 3, 9, 10, 12]
print(solution(sco, 7))


