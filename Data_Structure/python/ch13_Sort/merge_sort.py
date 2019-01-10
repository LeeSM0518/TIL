import random
list = []
sorted = []

for i in range(0, 20) :
    list.append(random.randrange(1,100))

for i in range(0, 20) :
    sorted.append(0)

def merge(list, left, mid, right, sorted) :
    i = left
    j = mid + 1
    k = left

    while i <= mid and j <= right :
        if list[i] <= list[j] :
            sorted[k] = list[i]
            k += 1
            i += 1
        else:
            sorted[k] = list[j]
            k += 1
            j += 1

    if i > mid :
        for l in range(j, right +1) :
            sorted[k] = list[l]
            k += 1
    else :
        for l in range(i, mid +1) :
            sorted[k] = list[l]
            k += 1

    list[left:right+1] = sorted[left:right+1]


def merge_sort(list, left, right, sorted) :
    if left < right :
        mid = (left + right) // 2
        merge_sort(list, left, mid, sorted)
        merge_sort(list, mid + 1, right, sorted)
        merge(list, left, mid, right, sorted)

merge_sort(list, 0, 19, sorted)

print(list)