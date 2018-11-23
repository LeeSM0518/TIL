graph = {'A': ['B', 'C', 'D'],
         'B': ['A', 'E', 'F'],
         'C': ['A', 'G'],
         'D': ['A', 'H', 'I'],
         'E': ['B'],
         'F': ['B', 'J'],
         'G': ['C'],
         'H': ['D'],
         'I': ['D', 'K', 'L'],
         'J': ['F'],
         'K': ['I'],
         'L': ['I']}

def DFS(graph, root) :
    stack = []
    visited = []

    stack.extend(root)

    while(stack):
        print('stack : ', stack)
        print('visited : ', visited)
        print('graph : ', graph,'\n')
        outputFromStack = stack.pop()
        visited.extend(outputFromStack)
        inputToStack = list(set(graph[outputFromStack]) - set(visited))
        inputToStack.sort()
        stack.extend(inputToStack)

    return  visited

print(DFS(graph, 'A'))