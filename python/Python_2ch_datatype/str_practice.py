# 문자열 곱하기
print('문자열 곱하기 : ','*' * 5,'\n')

# 문자 개수 세기
a='hobby'
print('a는 ',a,'이다','\nb의 개수는 ', a.count('b'))

#위치 알려주기
print('y의 위치는 ',a.find('y'))
print('o의 위치는 ',a.find('o'))
print('h의 위치는',a.index('h'),'\n')

#문자열 삽입
a = ","
print('a = {} 이고 abcd를 삽입하면 {}가 된다.\n' .format(a,a.join('abcd')))

#소문자 >> 대문자, 대문자 >> 소문자
a = 'hello'
b = 'HELLO'
print('a = {} 에서 대문자로 하면 {}이고 \nb = {} 에서 소문자로 하면 {}이다.\n'.format(a,a.upper(),b,b.lower()))

#공백 지우기
a = ' hi'
b = 'hi '
c = ' hi '
print("""a = {} 에서 왼쪽 공백을 지우면 {}이고
b = {}에서 오른쪽 공백을 지우면 {}이고
c = {}에서 양쪽 공백을 지우면 {}이다\n""".format(a, a.lstrip(), b, b.rstrip(), c, c.strip()))

#문자열 바꾸기
a = 'Life is too short'
print('a = {} 에서 Life를 바꾸면'.format(a))
print('{} 이다\n'.format(a.replace('Life','Your leg')))

#문자열 나누기
print('문자열 나누기')
a = 'Life is too short'
b = a.split()
print('a = {}이고 a를 공백으로 나눈 문자열은 {} 이다.\n'.format(a,b))

#공백 채우기
a = 'hi'
b = "{0:=^10}".format(a)
c = "{0:!<10}".format(a)
print('공백 채우기 a = {} 에서 변환 a = {}\n'.format(a,b))
print('공백 채우기 a = {} 에서 변환 a = {}\n'.format(a,c))

# f 포메팅
abc = 3
print(f'a = {abc}')

# isdigit
a = '123-123'
print('\na = ',a)
print('a[0].isdig = ',a[0].isdigit())
print('a[3].isdig = ',a[3].isdigit())