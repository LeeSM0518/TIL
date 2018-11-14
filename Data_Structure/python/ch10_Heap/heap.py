class HeapType:
    def __init__(self):
        self.maxCount = 0
        self.currentCount = 0
        self.pData = []

    def createArrayMaxHeap(self, max):
        if max > 0 :
             self.maxCount = max
             self.currentCount = 0
             for i in range(max) :
                 self.pData.append(None)
        else :
            print('최대 원소 개수는 0보다 커야 합니다')

    def insertMaxHeapAH(self, value):
        i = 0
        if self != None :
            if self.currentCount == self.maxCount :
                print('오류, 히프가 가득 찼습니다[{}], '
                      ' insertMaxHeap()'.format(self.maxCount))
                return None
            self.currentCount += 1
            i = self.currentCount
            while i != 1 and value > self.pData[int(i/2)] :
                self.pData[i] = self.pData[int(i/2)]
                i = int(i / 2)
            self.pData[i] = value
        return i

    def removeMaxHeapAH(self):
        pReturn = 0
        pTemp = 0
        cpy = 0

        parent = 1
        child = 2

        if self != None and self.currentCount > 0 :
            pReturn = self.pData[1]
            pTemp = self.pData[self.currentCount]
            self.currentCount -= 1

            while child <= self.currentCount :
                if child < self.currentCount and self.pData[child] < self.pData[child + 1]:
                    child += 1
                if pTemp >= self.pData[child] :
                    break
                self.pData[parent] = self.pData[child]
                parent = child
                child *= 2
            self.pData[parent] = pTemp
        return pReturn

    def deleteArrayMaxHeap(self) :
        self = None

    def displayArrayHeap(self):
        i = 0
        if self != None :
            for i in range(1, self.currentCount + 1) :
                print('{}, {}'.format(i, self.pData[i]))

if __name__ == '__main__':
    maxHeapSize = 20
    pHeap1 = HeapType()
    pNode = None

    pHeap1 = HeapType()
    pHeap1.createArrayMaxHeap(maxHeapSize)

    pHeap1.insertMaxHeapAH(90)
    pHeap1.insertMaxHeapAH(85)
    pHeap1.insertMaxHeapAH(80)
    pHeap1.insertMaxHeapAH(75)
    pHeap1.insertMaxHeapAH(70)
    pHeap1.insertMaxHeapAH(65)
    pHeap1.insertMaxHeapAH(60)
    pHeap1.insertMaxHeapAH(55)
    pHeap1.insertMaxHeapAH(50)
    pHeap1.insertMaxHeapAH(45)
    pHeap1.insertMaxHeapAH(40)
    pHeap1.insertMaxHeapAH(35)
    pHeap1.insertMaxHeapAH(30)

    print('Max Heap : ')
    pHeap1.displayArrayHeap()

    pHeap1.insertMaxHeapAH(99)
    print('\nAfter insertMaxHeapAH() - 99')
    print('Max Heap : ')
    pHeap1.displayArrayHeap()

    pNode = 0
    pNode = pHeap1.removeMaxHeapAH()
    print('remmoveMaxHeapAH() - [{}]'.format(pNode))
    print('Max Heap : ')
    pHeap1.displayArrayHeap()

    pHeap1.deleteArrayMaxHeap()