import random
list = []

for i in range(0, 20) :
    list.append(random.randrange(1,100))

def bubble_sort(list, n) :
    for i in range(n-1, -1, -1) :
        for j in range(0, i) :
            if list[j] > list[j+1] :
                list[j], list[j+1] = list[j+1], list[j]

bubble_sort(list, 20)

print(list)