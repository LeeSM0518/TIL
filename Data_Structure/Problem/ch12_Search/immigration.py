import Heap

def solution(n, times):
    answer = 0
    i = 0
    heap = Heap.Heap()
    for value in times :
        for i in range(1, n) :
            heap.insert_heap(value * i)
    print(heap.heap_data)
    for i in range(n - 1) :
        heap.remove_heap()
    print(heap.heap_data)
    answer = heap.heap_data[1]
    return answer

n = 6
times = [7, 10]
print(solution(n, times))