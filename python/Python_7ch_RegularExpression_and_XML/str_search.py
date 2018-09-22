# 정규식을 이용한 문자열 검색
import re

print('[a-z]+ 검색')
p = re.compile('[a-z]+')

m = p.match("python")
print('python = ', m)

m = p.match("3 python")
print('3 python = ' , m)

print('\nmatch 프로그램')
def match_str(st1, st2):
    p = re.compile(st1)
    m = p.match(st2)
    if m:
        print('Match fount : ', m.group())
    else:
        print('No match')

print('정규 표현식 : \'a.c\' 조사할 문자열 : \"abc\"  ')
match_str('a.c','abc')
print('정규 표현식 : \'a.c\' 조사할 문자열 : \"ddd\"  ')
match_str('a.c','ddd')

# search 함수
print('\nsearch 함수')
m = p.search("python")
print(m)
m = p.search("3 python")
print(m)

# findall 함수
print('\nfindall 함수')
print('p.findall("life is too short") = ')
result = p.findall("life is too short")
print(result)

# finditer 함수
print('\nfinditer 함수')
result = p.finditer("life is too short")
print(result)
for r in result : print(r)

    