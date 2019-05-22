import random
list = []

for i in range(0, 20) :
    list.append(random.randrange(1,100))

def quicksort(list):
    if len(list) <= 1:
        return list

    pivot = list[len(list) // 2]
    less = []
    more = []
    equal = []
    for a in list:
        if a < pivot:
            less.append(a)
        elif a > pivot:
            more.append(a)
        else:
            equal.append(a)

    return quicksort(less) + equal + quicksort(more)

list = quicksort(list)

print(list)