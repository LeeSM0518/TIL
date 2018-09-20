class ArrayListNode():
    """Node Data"""
    data = 0

class ArrayList():
    maxCount = 0
    currentCount = 0
    pData = {}

def createList(count):
    pReturn = ArrayList()
    pReturn.maxCount = count
    pReturn.currentCount = 0
    for i in range(count):
        pReturn.pData[i] = ArrayListNode()
    return pReturn

def addListData(pList, position, data):
    i = pList.currentCount - 1
    while i >= position:
        pList.pData[i+1].data = pList.pData[i].data
        i -= 1
    pList.pData[position].data = data
    pList.currentCount += 1

def removeListData(pList, position):
    i = position
    while i <= pList.currentCount -1 :
        pList.pData[i] = pList.pData[i+1]
        i += 1
    pList.currentCount -= 1

def getListData(pList, position):
    return pList.pData[position].data

def deleteList(pList):
    i = 0
    for i in range(pList.maxCount):
        del pList.pData[i]

print('메인')
pList = createList(5)

addListData(pList,0,10)
for i in range(5):
    print(pList.pData[i].data,end=' ')
print()
addListData(pList,1,20)
for i in range(5):
    print(pList.pData[i].data, end=' ')
print()
addListData(pList,0,30)
for i in range(5):
    print(pList.pData[i].data, end=' ')
print()

value = getListData(pList,1)
print('위치 = {}, 값 = {}\n'.format(1,value))

removeListData(pList,0)
for i in range(5):
    print(pList.pData[i].data, end=' ')
print()

removeListData(pList,1)
for i in range(5):
    print(pList.pData[i].data, end=' ')
print()

deleteList(pList)
print(pList.pData)
