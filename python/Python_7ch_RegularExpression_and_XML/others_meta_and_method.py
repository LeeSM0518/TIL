# 정규 표현식에 더 자세하게
import re

# {m} 메타 문자
print('\n{m} 메타문자')
p = re.compile('ca{2}t')
m = p.match('caaat')
print('m = p.match(\'caaat\') = ', m)

# ? 메타 문자
print('\n? 메타문자')
p = re.compile('ab?c')
m = p.match('abbc')
print('m = p.match(\'abbc\') = ', m)

# | 메타 문자 : or 역할
print('\n| 메타문자')
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

# 그룹핑된 문자열에 이름 붙이기
print('\n그룹핑된 문자열에 이름 붙이기')
p = re.compile(r"(?P<name>\w+)\s+((\d+)[-]\d+[-]\d+)")
m = p.search("park 010-1234-1234")
print(m.group('name'))

# 그룹명을 이용하여 정규식 내에서 재참조
print('\n그룹명을 이용하여 정규식 내에서 재참조')
p = re.compile(r'(?P<word>\b\w+)\s+(?P=word)')
m = p.search('Paris in the the spring').group()
print(m)

# 전방 탐색이 필요한 예시
print('\n전방 탐색이 필요한 예시')
p = re.compile('.+:')
m = p.search("http://google.com")
print(m.group())

# 긍정형 전방 탐색
print('\n긍정형 전방 탐색')
p = re.compile(".+(?=:)")
m = p.search("http://google.com")
print(m.group())

# 문자열 바꾸기
print('\n문자열 바꾸기')
p = re.compile('(blue|while|red)')
print(p.sub('colour','blue socks and red shoes'))
print(p.sub('colour','blue socks and red shoes',count=1))
print(p.subn('colour','blue socks and red shoes'))

# sub 메서드 사용 시 참조 구문 사용하기
print('\nsub 메서드 사용 시 참조 구문 사용하기')
p = re.compile(r"(?P<name>\w+)\s+(?P<phone>(\d+)[-]\d+[-]\d+)")
print(p.sub("\g<phone> \g<name>","park 010-1234-1234"))

# sub 메서드의 입력 인수로 함수 넣기
print('\nsub 메서드의 입력 인수로 함수 넣기')
def hexrepl(match):
    "Return the hex string for a decimal number"
    value = int(match.group())
    return hex(value)

p = re.compile(r'\d+')
print(p.sub(hexrepl, 'Call 65490 for printing, 49152 for user code.'))

# Greedy vs Non-Greedy
print('\nGreedy vs Non-Greedy')
s = '<html><head><title>Title</title>'
print(len(s))
print(re.match('<.*>',s).span())
print(re.match('<.*>',s).group())
print(re.match('<.*?>',s).group())