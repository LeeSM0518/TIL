def solution(n, computers):
    answer = n
    i = 0
    j = 0
    visited = []
    if n >= 1 and n <= 200 :
        while True :
            if i == n:      # 전부 다 확인시
                break
            elif j == n:    # 한 노드에 대한 간선을 다 확인시
                i += 1
                j = 0
            elif i == j :
                j += 1
            elif computers[i][j] == 1 : # 간선이 존재할 경우
                if visited == [] :  # 방문한 노드가 없을 경우
                    visited.append(i)
                    visited.append(j)
                    j += 1
                    answer -= 1
                elif visited.count(j) > 0: # 방문한 노드일 경우
                    j += 1
                else :  # 방문하지 않은 노드일 경우
                    visited.append(j)
                    answer -= 1
                    j += 1
            else :
                j+= 1
    return answer