def binarySearchRecursive(values, start, end, key) :
    ret = -1
    middle = 0

    if start <= end :
        middle = int((start + end) / 2)
        if key == values[middle] :
            ret = middle
        elif key < values[middle] :
            ret = binarySearchRecursive(values, start,
                                        middle - 1, key)
        else :
            ret = binarySearchRecursive(values, middle + 1,
                                        end, key)
    return ret

if __name__ == '__main__':
    key = 60
    ascSortedArray = [10, 20, 50, 60, 70, 80]

    index = binarySearchRecursive(ascSortedArray, 0, 5, key)

    print(index)