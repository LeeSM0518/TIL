class Heap :
    def __init__(self):
        self.heap_data = [None]

    def insert_heap(self, value):
        i = 0
        if self != None :
            if len(self.heap_data) != 1 :
                i = len(self.heap_data)
                while i != 1 and value > self.heap_data[int(i/2)] :
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
                if child < len(self.heap_data) - 1 and self.heap_data[child] < self.heap_data[child + 1]:
                    child += 1
                if pTemp >= self.heap_data[child] :
                    break
                self.heap_data[parent] = self.heap_data[child]
                parent = child
                child *= 2
            del self.heap_data[parent]
            self.heap_data.insert(parent, pTemp)
        return  pReturn