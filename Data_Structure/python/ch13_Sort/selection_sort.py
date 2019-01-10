import random

def selection_sort(list, n) :
    for i in range(0, n-1) :
        least = i
        for j in range(i+1, n) :
            if list[j] < list[least] :
                least = j
        list[i], list[least] = list[least], list[i]

list = []

for i in range(0, 20) :
    list.append(random.randrange(1,100))

selection_sort(list, 20)

print(list)