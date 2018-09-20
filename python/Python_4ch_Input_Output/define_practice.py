#여러 개의 입력값을 받는 함수 만들기

def sum_many(*args): # 입력 변수명 앞에 *을 붙이면 입력값들을 전부 모아서 튜플로 만들어 주기 때문.
    sum = 0
    for i in args:
        sum = sum + i
    return sum

print('sum = ', sum_many(1,2,3,4,5))
print()

# 함수의 결과 값은 언제나 하나이다.
def sum_and_mul(a,b):
    return a+b, a*b
    # 튜플 값으로 반환된다.

result = sum_and_mul(3,4)
print('result = ',result)
print()

# return의 다른 쓰임새
def say_nick(nick):
    if nick == '바보':
        print('return 실행')
        return
    print('나의 별명은 %s입니다.'%nick)
say_nick('야옹')
say_nick('바보')
print()

# 입력 인수에 초깃값 미리 설정하기
def say_myself(name, old, man=True):
    print('나의 이름은 {} 입니다.'.format(name))
    print('나이는 {}살 입니다.'.format(old))
    if man :
        print('남자입니다.')
    else:
        print('여자입니다.')
say_myself('min',21)
print()
say_myself('girl',21,False)
print()
# 함수에 (name, old, man = True) 는 되지만 ( name, man = True, old) 는 안된다.

# 함수 안에서 선언된 변수의 효력 범위
a = 1
def vartest(a):
    a = a + 1

print('a = {}'.format(vartest(a)))
print('a = {}'.format(a))
# 함수 안에서 새로 만들어진 변수는 함수 안에서만 사용되는 '함수만의 변수'이다.
# 즉, 함수 안에서 사용되는 변수는 함수 밖의 변수 이름들과는 전혀 상관이 없다는 말이다.
print()

# global 명령어 이용하기
a = 1
def vartest():
    global a
    a = a + 1

vartest()

print('global a = {}'.format(a))
