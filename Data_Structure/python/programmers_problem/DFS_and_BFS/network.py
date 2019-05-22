def dfs(graph, start, startLow, startColumn, answer) :
    if (len(graph) - 1) == startLow and \
        (len(graph) - 1) == startColumn :
        return answer
    elif startColumn == len(graph) - 1 :
        answer = dfs(graph, start, startLow + 1, 0, answer)
    elif graph[startLow][startColumn] == 1 and \
            startColumn != start :
        answer -= 1
        graph[startLow][startColumn] = 0
        graph[startColumn][startLow] = 0
        answer = dfs(graph, start, startColumn , 0, answer)
    elif graph[startLow][startColumn] == 1 and \
            startColumn == start :
        graph[startLow][startColumn] = 0
        graph[startColumn][startLow] = 0
        return answer
    else :
        answer = dfs(graph, start, startLow, startColumn + 1, answer)
    return answer

def solution(n, computers) :
    ans = n
    i = 0
    for this in computers :
        this[i] = 0
        i += 1
    print(computers)
    for j in range(n) :
        for k in range(n) :
            if computers[j][k] == 1 :
                ans = dfs(computers, j, j, k, ans)
    return ans


computers = [ [1, 1, 1, 1, 0],
              [1, 1, 1, 0, 0],
              [1, 1, 1, 1, 0],
              [1, 0, 1, 1, 0],
              [0, 0, 0, 0, 1] ]

if 1 not in computers :
    print('not')

print(solution(5, computers))