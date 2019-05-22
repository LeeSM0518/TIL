def solution(scoville, k) :
    scoville.sort()
    out = 0
    answer = 0
    while(True):
        Return = scoville[0] + scoville[1] * 2
        answer += 1
        scoville[0:2] = []
        scoville.append(Return)
        scoville.sort()
        for i in scoville :
            if i < k :
                break
            else :
                out += 1
        if out == len(scoville):
            break
        else :
            out = 0
    return answer

sco = [1, 2, 3, 9, 10, 12]
print(solution(sco, 7))