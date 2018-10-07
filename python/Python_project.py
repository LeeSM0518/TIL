class CableNode:
    def CableNodeMake(self):
        self.title = None
        self.content = None
        self.creater = None
        self.day = None
        self.time = None

class Cable:
    def CableMake(self):
        self.data = CableNode()
        self.data.CableNodeMake()
        self.dic = {}
        self.currentCount = 0

def print_menu(ch):
    print("==================")
    print("\t1. 추가  ")
    print("\t2. 출력  ")
    print("\t3. 검색  ")
    print("\t4. 수정  ")
    print("\t5. 삭제  ")
    print("\t6. 이전으로  ")
    print("==================")
    ch = input("메뉴를 선택하시오 : ")
    print()
    return ch

def print_kms(ch):
    print("==================")
    print("\t방송사 선택  ")
    print("\t1. KBS  ")
    print("\t2. MBC  ")
    print("\t3. SBS  ")
    print("\t4. 종료  ")
    print("==================")
    ch = input("방송사를 선택하시오 : ")
    print()
    return ch

def Cable_Plus(pCable):
    pNode = CableNode()
    pNode.CableNodeMake()
    pNode.title = input("방송제목 : ")
    pNode.content = input('분야 : ')
    pNode.creater = input('제작진 : ')
    pNode.day = input('방송하는 요일(월, 화, 수, ... ) : ')
    pNode.time = input('시작시간~끝나는시간 ex) 12:00~13:00 : ')
    print()
    pCable.dic[pCable.currentCount] = pNode
    pCable.currentCount += 1
    return pCable

pCable = 0
pCable = Cable()
pCable.CableMake()
pCable = Cable_Plus(pCable)

def check_time(pCable):
    if pCable.currentCount > 1 :
        for i in range(pCable.currentCount) :
            j = i + 1
            for j in range(pCable.currentCount) :
                '''if pCable.dic[i].time'''

    elif pCable.currentCount == 1:
        print('방송제목 : {}'.format(pCable.dic[0].title))
        print('분야 : ', pCable.dic[0].content)
        print('제작진 : ', pCable.dic[0].creater)
        print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[0].day)
        print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[0].time)
        print()
    else :
        print('빈 리스트 입니다.')
        print()

