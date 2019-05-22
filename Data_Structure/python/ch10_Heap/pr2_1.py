from heapq import heappush, heappop

a = [3,5,2,1,7,6]
heap = []

for item in a :
    heappush(heap, item)

print(heap)
