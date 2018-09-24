# 정규 표현식에 더 자세하게
import re

# | 메타 문자 : or 역할
print('| 메타문자')
p = re.compile('Crow|Servo')
m = p.match('CrowHello')
print('p.match(\'CrowHello\')= ', m)

# ^  : 문자열의 맨 처음
print('\n^ 메타문자'  )
print('re.search(\'^Life\', \'Life is too short\') = ', re.search('^Life','Life is too short'))
print('re.search(\'^Life\', \'short Life\') = ',re.search('^Life', 'short Life'))

# $ : 문자열의 끝
print('\n$ 메타문자')
print('re.search(\'short$\', \'Life is too short\') = ',re.search('short$','Life is too short'))
print('re.search(\'short$\',\'Life is too short, you\') =' , re.search('short$','Life is too short, you'))

# \A : 전체 문자열의 처음과 매치됨.
print('\n\A 메타문자')
p = re.compile('\ALife',re.MULTILINE)
data ="""life
Life is
"""
m = p.search(data)
print(m)

# \Z : 전체 문자열의 끝과 매치 됨.
print('\n\Z 메타문자')
p = re.compile('\Zshort',re.MULTILINE)
data = """Life
is too short,
you need python"""
m = p.search(data)
print(m)

# \b : whitespace에 의해 구분이 된다.
print('\n\\b 메타문자')
p = re.compile(r'\bclass\b')
print(p.search('no class at all'))
print(p.search('the declassified'))

# \B : \b와 반대의 경우
print('\n\\B 메타문자')
p = re.compile(r"\Bclass\B")
print(p.search('no class at all'))
print(p.search('the declassified algorithm'))

# 그룹핑
print('\n그룹핑 예시 1)')
p = re.compile('(ABC)+')
m = p.search('ABCABCABC OK?')
print(m)
print(m.group())

print('\n그룹핑 예시 2)')
p = re.compile(r"\w+\s+\d+[-]\d+[-]\d+")
m = p.search("park 010-1234-1234")
print(m)

print('\n그룹핑 예시 3)')
p = re.compile(r'(\w+)\s+\d+[-]\d+[-]\d+')
m = p.search('park 010-1234-1234')
print(m.group(1))

print('\n그룹핑 예시 4)')
p = re.compile(r'(\w+)\s+((\d+)[-]\d+[-]\d+)')
m = p.search('park 010-1234-1234')
print(m.group(1))
print(m.group(2))
print(m.group(3))

# 그룹핑된 문자열 재참조하기
print('\n그룹핑된 문자열 재참조')
p = re.compile(r'(\b\w+)\s+\1') # \1 은 그룹 중 첫 번째 그룹을 지칭한다.
print(p.search('Paris in the the spring').group())
