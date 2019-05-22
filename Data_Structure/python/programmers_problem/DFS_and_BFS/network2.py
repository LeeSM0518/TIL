def solution(n, computers) :
    start = [0, 0]
    visited = {}
    answer = n
    i = 0
    j = 0
    low = 0
    column = 0
    while low < n  and column < n:
        if column == n - 1 :
            low += 1
            column = 0
        elif low == n - 1 and column == n - 1:
            for i in range(n) :
                for j in range(n) :
                    if computers[i][j] == 1 :
                        start = [i, j]
                        break
                if computers[i][j] == 1:
                    break
        elif computers[low][column] == 1 :
            if low == column :
                column += 1
                computers[low][column] = 0
            elif start[0] not in visited :
                visited[start[0]] = [column]
                computers[low][column] = 0
                if computers:
                    ''''''
            else :
                if column in visited[start[0]] :
                    computers[low][column] = 0
                    column += 1
    return answer

computers = [ [1, 1, 0],
              [1, 1, 0],
              [0, 0, 1] ]
n = 3