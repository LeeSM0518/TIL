import re

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
    pNode.time = input('시작시간~끝나는시간 ( ex) 12:00~13:00) : ')
    print()
    pCable.dic[pCable.currentCount] = pNode
    pCable.currentCount += 1
    return pCable

def check_time(pCable):
    pNewCable = 0
    pNewCable = Cable()
    pNewCable.CableMake()
    pNewCable = pCable

    pNode = CableNode()
    pNode.CableNodeMake()

    if pCable.currentCount > 1 :
        p = re.compile('\d+(?![:])\d+')
        for i in range(pCable.currentCount) :
            j = i + 1
            f = p.findall(pCable.dic[i].time)
            time_i = int(f[0]) * 100 + int(f[1])
            while True :
                if j == int(pCable.currentCount) :
                    break
                f = p.findall(pCable.dic[j].time)
                time_j = int(f[0]) * 100 + int(f[1])
                if time_i > time_j :
                    pNewCable.dic[i],pNewCable.dic[j] = pCable.dic[j], pCable.dic[i]
                j += 1
        i = 0
        for i in range(pCable.currentCount) :
            pNode = pNewCable.dic[i]
            print('방송제목 : {}'.format(pNode.title))
            print('분야 : ', pNode.content)
            print('제작진 : ', pNode.creater)
            print('방송하는 요일(월, 화, 수, ... ) : ', pNode.day)
            print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pNode.time)
            print()
    elif pCable.currentCount == 1:
        print('방송제목 : {}'.format(pCable.dic[0].title))
        print('분야 : ', pCable.dic[0].content)
        print('제작진 : ', pCable.dic[0].creater)
        print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[0].day)
        print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[0].time)
        print()
    else :
        print('없습니다.')
        print()


def check_days(pCable):
    search = input('검색하실 요일을 입력하세요 : ')
    if pCable.currentCount > 1 :
        i = 0
        while True:
            if i == int(pCable.currentCount) :
                break
            if pCable.dic[i].day == search :
                print('방송제목 : {}'.format(pCable.dic[i].title))
                print('분야 : ', pCable.dic[i].content)
                print('제작진 : ', pCable.dic[i].creater)
                print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[i].day)
                print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[i].time)
                print()
            i += 1
    elif pCable.currentCount == 1 and pCable.dic[0].day == search:
        print('방송제목 : {}'.format(pCable.dic[0].title))
        print('분야 : ', pCable.dic[0].content)
        print('제작진 : ', pCable.dic[0].creater)
        print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[0].day)
        print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[0].time)
        print()
    else :
        print('없습니다.')
        print()
'''
Newcable = Cable()
Newcable.CableMake()

Newcable = Cable_Plus(Newcable)
Newcable = Cable_Plus(Newcable)
'''

def check_search(pCable):
    search = input('검색하실 방송이름을 입력하세요 : ')
    print()
    if pCable.currentCount > 1 :
        i = 0
        while True:
            if i == int(pCable.currentCount) :
                break
            if pCable.dic[i].title == search :
                print('방송제목 : {}'.format(pCable.dic[i].title))
                print('분야 : ', pCable.dic[i].content)
                print('제작진 : ', pCable.dic[i].creater)
                print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[i].day)
                print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[i].time)
                print()
            i += 1
    elif pCable.currentCount == 1:
        print('방송제목 : {}'.format(pCable.dic[0].title))
        print('분야 : ', pCable.dic[0].content)
        print('제작진 : ', pCable.dic[0].creater)
        print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[0].day)
        print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[0].time)
        print()
    else :
        print('없습니다.')
        print()

def check_adjust(pCable) :
    search = input('수정하실 방송 이름을 입력하세요 : ')
    i = 0
    while True :
        if i == int(pCable.currentCount):
            print('입력하신 방송이 없습니다.')
            break
        if pCable.dic[i].title == search:
            print('방송제목 : {}'.format(pCable.dic[i].title))
            print('분야 : ', pCable.dic[i].content)
            print('제작진 : ', pCable.dic[i].creater)
            print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[i].day)
            print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[i].time)
            print()
            break
        i += 1
    adjust = input('수정하실 부분을 선택하세요 (제목, 분야, 제작진, 요일, 방송시간) : ')
    if adjust == '제목' :
        change = input('방송제목 : ')
        pCable.dic[i].title = change
    elif adjust == '분야' :
        change = input('분야 : ')
        pCable.dic[i].content = change
    elif adjust == '제작진' :
        change = input('제작진 : ')
        pCable.dic[i].creater = change
    elif adjust == '요일' :
        change = input('방송하는 요일(월, 화, 수, ... ) ')
        pCable.dic[i].day = change
    elif adjust == '방송시간' :
        change = input('시작시간~끝나는시간 ( ex) 12:00~13:00 ) : ')
        pCable.dic[i].time = change
    print()
    print('방송제목 : {}'.format(pCable.dic[i].title))
    print('분야 : ', pCable.dic[i].content)
    print('제작진 : ', pCable.dic[i].creater)
    print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[i].day)
    print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[i].time)
    print()
    return pCable

def check_delete(pCable):
    search = input('삭제하실 방송이름을 입력하세요 : ')
    if pCable.currentCount > 1 :
        i = 0
        while True:
            if i == int(pCable.currentCount) :
                break
            if pCable.dic[i].title == search :
                print('방송제목 : {}'.format(pCable.dic[i].title))
                print('분야 : ', pCable.dic[i].content)
                print('제작진 : ', pCable.dic[i].creater)
                print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[i].day)
                print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[i].time)
                print('이 방송을 삭제합니다.')
                print()
                while True :
                    if i == int(pCable.currentCount) - 1 :
                        break
                    pCable.dic[i] = pCable.dic[i+1]
                    i += 1
                del pCable.dic[pCable.currentCount - 1]
                pCable.currentCount -= 1
                break
            i += 1
        return pCable
    elif pCable.currentCount == 1:
        print('방송제목 : {}'.format(pCable.dic[0].title))
        print('분야 : ', pCable.dic[0].content)
        print('제작진 : ', pCable.dic[0].creater)
        print('방송하는 요일(월, 화, 수, ... ) : ', pCable.dic[0].day)
        print('시작시간~끝나는시간 ex) 12:00~13:00 : ', pCable.dic[0].time)
        print('이 방송을 삭제합니다.')
        print()
        del pCable.dic[0]
        pCable.currentCount -= 1
        return pCable
    else :
        print('없습니다.')
        print()