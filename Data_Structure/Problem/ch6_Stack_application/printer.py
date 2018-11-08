def solution(priorities, location):
    answer = 1
    max = priorities[0]
    printer_queue = priorities[:]
    cpy = 0
    list = []
    for i in range(len(priorities)) :
        list.append(i)
        if max < priorities[i] :
            max = priorities[i]
    for i in range(len(priorities)):
        if max > priorities[i] :
            cpy = list[0]
            del list[0]
            list.append(cpy)
        elif max < priorities[i] :
            continue
        else :
            break
    for i in range(len(priorities)):
        if list[i] != location :
            answer += 1
        else :
            break
    return answer
