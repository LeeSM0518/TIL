class Heap :
    def __init__(self):
        self.heap_data = [None]

    def insert_heap(self, value):
        i = 0
        if self != None :
            if len(self.heap_data) != 1 :
                i = len(self.heap_data)
                while i != 1 and value < self.heap_data[int(i/2)] :
                    i = int(i/2)
                self.heap_data.insert(i, value)
            else :
                self.heap_data.append(value)

    def remove_heap(self):
        pReturn = 0
        pTemp = 0
        parent = 1
        child = 2
        pReturn = self.heap_data[1]
        if self != None and len(self.heap_data) != 1 :
            pTemp = self.heap_data[len(self.heap_data) - 1]
            del self.heap_data[len(self.heap_data) - 1]
            while child <= len(self.heap_data) - 1 :
                if child < len(self.heap_data) - 1 and self.heap_data[child] > self.heap_data[child + 1]:
                    child += 1
                if pTemp <= self.heap_data[child] :
                    break
                self.heap_data[parent] = self.heap_data[child]
                parent = child
                child *= 2
            del self.heap_data[parent]
            self.heap_data.insert(parent, pTemp)
        return  pReturn

if __name__ is '__main__' :
    A = Heap()
    A.insert_heap(1)
    A.insert_heap(2)
    A.insert_heap(4)
    A.insert_heap(3)
    A.insert_heap(5)
    A.insert_heap(6)
    A.insert_heap(7)
    A.insert_heap(9)
    A.insert_heap(10)
    A.insert_heap(11)
    A.insert_heap(12)
    A.insert_heap(13)
    A.insert_heap(14)
    A.insert_heap(15)
    A.insert_heap(16)
    A.insert_heap(17)
    A.insert_heap(19)
    A.insert_heap(20)
    print(A.heap_data)

    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)
    A.remove_heap()
    print(A.heap_data)