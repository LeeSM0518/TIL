def solution(operations):
    op_list = []  # operations를 분할하여 저장할 리스트
    answer = []  # [최대값, 최소값] 을 저장할 리스트
    heap = []  # op 연산을 처리하여 저장할 힙
    for i in operations :
        op_list.append(i.split())
    i = 0
    while i < len(operations) :
        if op_list[i][0] == 'I' :
            heap.append(int(op_list[i][1]))
        elif op_list[i][0] == 'D' :
            if len(heap) == 0 :
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