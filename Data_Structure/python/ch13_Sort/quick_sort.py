import random
list = []

for i in range(0, 20) :
    list.append(random.randrange(1,100))

def quick_sort(list, left, right) :
    if left < right :
        q = partition(list, left, right)
        quick_sort(list, left, q - 1)
        quick_sort(list, q + 1, right)

def partition(list, left, right) :
    low = left
    high = right
    pivot = list[left]

    while low < high :
        while low <= high and list[low] <= pivot :
            low += 1
        while low <= high and list[high] >= pivot :
            high -= 1
        if low < high :
            list[low], list[high] = list[high], list[low]
    list[left], list[high] = list[high], list[left]
    return high

quick_sort(list, 0, 19)

print(list)