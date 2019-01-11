def dfs(graph, n, start, visited) :
    vertexList = [ i for i in range(n)]
    edgeList = []
    visitedVertex = []
    stack = [start]
    for i in range(n) :
        for j in range(n) :
            if i == j :
                j += 1
            elif graph[i][j] == 1 :
                edgeList.append([i, j])
    adjacencyList = [ [] for vertex in vertexList]
    for edge in edgeList :
        adjacencyList[edge[0]].append(edge[1])
    while stack :
        current = stack.pop()
        for neighbor in adjacencyList[current] :
            if not neighbor in visitedVertex :
                stack.append(neighbor)
        if current not in visitedVertex:
            visitedVertex.append(current)
    return visitedVertex

def solution(n, computers) :
    visited = []
    visitedBefore = []
    network = []
    for i in range(n) :
        visited.append(dfs(computers, n, i, visitedBefore))
        visitedBefore = visited[i][:]
    print(visitedBefore)
    print(visited)
    answer = len(visited)
    return answer

#computers = [[1, 1, 0],
#             [1, 1, 0],
#             [0, 0, 1]]

computers = [ [1, 1, 1, 1, 0],
              [1, 1, 1, 0, 0],
              [1, 1, 1, 1, 0],
              [1, 0, 1, 1, 0],
              [0, 0, 0, 0, 1] ]

n = 5

print(solution(n,computers))