def solution(tickets) :
    vertexList = set()
    adjacencyList = {}
    visitedVertex = []
    stack = []
    for vertex in tickets:
        for i in range(2):
            vertexList.add(vertex[i])
        if vertex[0] == 'ICN' :
            stack.append('ICN')
    edgeList = tickets[:]
    for vertex in vertexList:
        adjacencyList[vertex] = set()
    for edge in edgeList:
        adjacencyList[edge[0]].add(edge[1])
    print(adjacencyList)
    while stack :
        print('stack = ' , stack)
        current = stack.pop()
        print('current = ', current)
        for neighbor in adjacencyList[current] :
            print('neighbor = ', neighbor)
            if not neighbor in visitedVertex :
                stack.append(neighbor)
                print('stack = ', stack)
        visitedVertex.append(current)
        print('visitedVertex = ', visitedVertex)
    return visitedVertex

#tickets = [['ICN', 'JFK'], ['HND', 'IAD'], ['JFK', 'HND']]
tickets = [['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL','SFO']]

print(solution(tickets))