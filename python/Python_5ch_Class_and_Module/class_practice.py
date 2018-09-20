# __init__ 이란?
print('__init__ 이란?')
class Service:
    secret = 'Python Programming'
    def __init__(self,name):
        self.name = name
    def sum(self,a ,b):
        result = a + b
        print('{}님 {} + {} = {} 입니다.'.format( self.name , a, b, result))

pey = Service('홍길동')
pey.sum(3, 5 )
print()

# 객체에 숫자 지정할 수 있게 만들기
print('객체에 숫자 지정할 수 있게 만들기')
class FourCal:
    # setdata라는 함수는 FourCal 클래스의 메서드 이다.
    def setdata(self,first,second): # 메서드의 입력 인수
        self.first = first      # 메서드의 수행문
        self.second = second    # ""
    def sum(self):
        result = self.first + self.second
        return result
    def mul(self):
        result = self.first * self.second
        return result
    def sub(self):
        result = self.first - self.second
        return result
    def div(self):
        result = self.first / self.second
        return result

a = FourCal()
a.setdata(4, 2)
print('a.first = {}'.format(a.first))
print('a.second = {}'.format(a.second))

b = FourCal()
b.setdata(3, 7)
print('b.first = {}'.format(b.first))
print('b.second = {}'.format(b.second))

print('a.sum = {} '.format(a.sum()))
print('a.mul = {}'.format(a.mul()))
print('a.sub = {} '.format(a.sub()))
print('a.div = {}'.format(a.div()))
print()

# 박씨네 집 클래스 만들기
print('박씨네 집 클래스 만들기')

class HousePark():
    """박씨네 집 클래스"""
    lastname = '박'
    def setname(self,name):
        self.fullname = self.lastname + name
    def travel(self,where):
        self.where = where
        print('{}, {}여행을 가다.'.format(self.fullname, self.where))

pey = HousePark()
print(pey.lastname)
pey.setname('응용')
print('full name = {}'.format(pey.fullname))
pey.travel('부산')

# 초깃값 설정하기
print('\n__init__ 메서드로 초깃값을 설정')
class HousePark():
    lastname = '박'
    def __init__(self,name):    # __init__ 메서드를 사용하면 pey.setname 메서드를 사용하지 않아도 된다.
        self.fullname = self.lastname + name
    def travel(self, where):
        print('{}, {}여행을 가다.'.format(self.fullname, where))

pey = HousePark('응용')
pey.travel('태국')

# 클래스의 상속
print('\n클래스의 상속')
class HouseKim(HousePark):
    lastname = '김'

juliet = HouseKim('줄리엣')
juliet.travel('독도')

class HouseKim(HousePark):
    lastname = '김'
    def travel(self, where, day):
        print('{}, {}여행 {}일 가네.'.format(self.fullname, where, day))

juliet = HouseKim('줄리엣')
juliet.travel('독도',3)

# 연산자 오버로딩
print('\n연산자 오버로딩')

class HousePark():
    lastname = '박'
    def __init__(self,name):
        self.fullname = self.lastname + name
    def travel(self,where):
        print('{}, {}여행을 가다.'.format(self.fullname, where))
    def love(self, other):
        print('{}, {} 사랑에 빠졌네'.format(self.fullname, other.fullname))
    def __add__(self, other):   # add 메서드
        print('{}, {} 결혼했네'.format(self.fullname, other.fullname))

class HouseKim(HousePark):
    lastname = '김'
    def travel(self,where, day):
        print('{}, {}여행 {}일 가네.'.format(self.fullname, where, day))

pey = HousePark('응용')
juliet = HouseKim('줄리엣')
pey.love(juliet)
pey + juliet

# '박씨네 집' 클래스 완성하기
print('\n박씨네 집 클래스 완성하기')
class HousePark():
    lastname = '박'
    def __init__(self, name):
        self.fullname = self.lastname + name
    def travel(self, where):
        print('{}, {}여행을 가다'.format(self.fullname, where))
    def love(self, other):
        print('{}, {} 사랑에 빠졌네'.format(self.fullname, other.fullname))
    def __add__(self, other):
        print('{}, {} 결혼했네'.format(self.fullname, other.fullname))
    def __sub__(self, other):
        print('{}, {} 이혼했네'.format(self.fullname, other.fullname))
    def fight(self, other):
        print('{}, {} 싸우네'.format(self.fullname, other.fullname))

class HouseKim(HousePark):
    lastname = '김'
    def travel(self, where, day):
        print('{} {}여행 {}일 가네'.format(self.fullname, where, day))

pey = HousePark('응용')
juliet = HouseKim('줄리엣')
pey.travel('부산')
juliet.travel('부산',3)
pey.love(juliet)
pey + juliet
pey.fight(juliet)
pey - juliet