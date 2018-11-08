def solution(arrangement):
    stack = []
    answer = 0
    before = arrangement[0]
    for i in arrangement :
        if i == ')' :
            if stack != [] :
                if before == '(' :
                    stack.pop()
                    answer += len(stack)
                else :
                    stack.pop()
                    answer += 1
            else :
                stack.append(i)
        else :
            stack.append(i)
        before = i
    return answer
arr_ori = '()(((()())(())()))(())'
print(solution(arr_ori))