# abs 함수 : 숫자의 절대값을 돌려주는 함수
print('abs 함수')
print(abs(3))
print(abs(-1.2))

# all 함수 : 반복 가능한 자료형 x를 입력 인수로 받으며, 이 x가 모두 참이면 True, 거짓이 하나라도 있으면 False 를 리턴
print('\nall 함수')
print('all([1,2,3]) = ',all([1,2,3]))
print('all([1,2,3,0]) = ',all([1,2,3,0]))

# any 함수 : x 중 하나라도 참이 있는 경우 True , x가 모두 거짓일 경우 False
print('\nany 함수')
print('any([1,2,3,0] = ', any([1,2,3,0]))
print('any([0,""] = ', any([0,""]))

# chr 함수 : 아스키 코드값을 입력으로 받아 그 코드에 해당하는 문자를 출력
print('\nchr 함수')
print('chr(97) = ',chr(97))
print('chr(48) = ',chr(48))

# divmod 함수 : 2개의 숫자를 입력으로 받는다. 그리고 a를 b로 나눈 몫과 나머지를 튜플 형태로 리턴한다.
print('\ndivmod 함수')
print('divmod(7,3) = ',divmod(7,3))
print('divmod(1.3,0.2) = ',divmod(1.3,0.2))

# enumerate 함수 : 자료형을 입력받아 인덱스 값을 포함하는 객체 리턴
print('\nenumerate 함수')
for i, name in enumerate(['body','foo','bar']):
    print(i,name)

# eval 함수 : 실행 가능한 문자열 ( 1+2, 'hi' + 'a' )을 입력 받아 문자열을 실행한 결과값 리턴
print('\neval 함수')
print('eval(\'1+2\') = ',eval('1+2'))
print('eval(\"\'hi\'+\'a\'\") = ',eval("'hi'+'a'"))
print('eval(\'divmod(4,3)\') = ',eval('divmod(4,3)'))

# filter 함수 : 첫 번째 인수로 함수 이름을, 두 번째 인수로 그 함수에 차례로 들어갈 반복 가능한 자료형을 받는다.
#               그리고 두 번째 인수인 반복 가능한 자료형 요소들이 첫 번째 인수인 함수에 입력되었을 때 리턴값이 참인 것만 묶어서 돌려준다.
print('\nfilter 함수')
def positive(numberList):
    result=[]
    for num in numberList:
        if num > 0:
            result.append(num)
    return result

print(positive([1,-3,2,0,-5,6]))

def positive2(x):
    return x>0

print('filter 사용 = ',list(filter(positive2,[1,-3,2,0,-5,6])))
print('lambda 추가 사용 = ',list(filter(lambda x:x>0,[1,-3,2,0,-5,6])))

# hex 함수 : 정수값을 입력받아 16진수로 변환하여 리턴하는 함수
print('\nhex함수')
print('hex(234) = ',hex(234))
print('hex(3) = ',hex(3))

# id 함수 : 객체를 입력받아 객체의 고유 주소값을 리턴하는 함수이다.
print('\nid 함수')
a = 3
print('id(3) = ',id(3))
print('id(a) = ',id(a))
b = a
print('id(b) = ',id(b))

# isinstance 함수 : 첫 번째 인수로 인스턴스, 두 번째 인수로 클래스 이름을 받는다. 입력으로 받은 인스턴스가 그 클래스의 인스턴스 인지 판단.
print('\nisinstance 함수')
class Person: pass
a = Person()
print('isinstance(a,Person) = ',isinstance(a, Person))
b = 3
print('isinstance(b,Person) = ',isinstance(b, Person))

# lambda 함수 : def와 동일한 역할을 한다.
# lambda 인수1, 인수2, ... : 인수를 이용한 표현식
print('\nlambda 함수')
def sum(a,b):
    return a+b
print('sum(3,4) = ',sum(3,4))
sum2 = lambda a,b : a + b
print('lambda 를 이용한 sum2(3,4) = ',sum2(3,4))

myList = [lambda a,b : a+b, lambda a,b : a*b]
print('myList = ',myList,' 로서 람다 함수 2개가 리스트로 추가 됨')
print('myList[0](3,4) = ',myList[0](3,4))

# map(함수, 자료형) 함수 : 함수와 반복 가능한 자료형을 입력. 입력 받은 자료형의 각 요소가 함수 f에 의해 수행된 결과를 묶어서 리턴
print('\nmap 함수')
def two_times(numberList):
    result = []
    for number in numberList:
        result.append(number * 2)
    return result

result = two_times([1,2,3,4])
print('일반 함수 이용result = ',result)

def two_times(x): return x*2
print('map 이용 list = ',list(map(two_times,[1,2,3,4])))

print('map과 lambda 이용 list = ', list(map(lambda a:a*2,[1,2,3,4])))

def plus_one(x):
    return x + 1
print('map 예제 list = ',list(map(plus_one,[1,2,3,4,5])))

# max 함수 : 최대값 리턴
print('\nmax 함수')
print('max([1,2,3]) = ',max([1,2,3]))
print('max(\'python\') = ',max('python'))

# min 함수 : 최소값 리턴
print('\nmin 함수')
print('min([1,2,3]) = ',min([1,2,3]))
print('min(\'python\') = ',min('python'))

# oct(x) 함수 : 정수 형태의 숫자를 8진수 문자열로 리턴
print('\noct 함수')
print('oct(34) = ',oct(34))
print('oct(12345) = ',oct(12345))

# pow(x, y) 함수 : x의 y 제곱한 결과값을 리턴 함수.
print('\npow 함수')
print('pow(2,4) = ',pow(2,4))
print('pow(3,3) = ',pow(3,3))

# round( number ) 함수 : 숫자를 입력받아 반올림
print('\nround 함수')
print('round(4.6) = ',round(4.6))

# sorted 함수 : 입력값을 정렬한 후 그 결과를 리스트로 리턴.
print('\nsorted 함수')
print('sorted([3, 1, 2] = ', sorted([3,1,2]))
print('sorted("zero") = ', sorted("zero"))

# str 함수 : 문자열 형태로 객체를 변환하여 리턴
print('\nstr 함수')
print(id(3))
print('str(3) = ',str(3),'\nid(str(3)) = ',id(str(3)))

# zip(iterable*) 함수 : 동일한 개수로 이루어진 자료형을 묶어 주는 역할을 하는 함수
print('\nzip 함수')
print('list(zip([1,2,3],[4,5,6])) = ', list(zip([1,2,3],[4,5,6])))
