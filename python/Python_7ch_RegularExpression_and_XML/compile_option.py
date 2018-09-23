# 컴파일 옵션
print('DOTALL 옵션')
import re
p = re.compile('a.b',re.DOTALL)
m = p.match('a\nb')
print(m)

print('\nIGNORECASE 옵션')
p = re.compile('[a-z]+',re.I)
print('p.match(\'python\') = ', p.match('python'))
print('p.match(\'PYTHON\') = ', p.match('PYTHON'))

print('\nMULTILINE 옵션')
p = re.compile("^python\s\w+")

data = """python none
life is too short
python two
you need python
python three"""
print('옵션을 사용하지 않은 예')
print('p.findall(data) = ', p.findall(data))
# ^ 메타 문자에 의해 첫 번째 라인만 매치가 됨

p = re.compile("^python\s\w+", re.MULTILINE)
print('옵션을 사용한 예')
print('p.findall(data) = ', p.findall(data))
# 옵션이 각 라인 마다 메타 문자를 적용시키도록 한다.

# VERBOSE 옵션 : 어려운 정규식을 주석 또는 라인 단위로 구분하게 해준다.
charref = re.compile(r"""
&[#]    # 숫자의 엔티티 참조 시작
(
     0[0-7]+    # 8진법
     | [0-9]+   # 10진법
     | x[0-9a-fA-F]+    # 16진법
)
;
""", re.VERBOSE)
print('\nVERBOSE 옵션')
p = '000'
print('charref.search(\'000\') = ', charref.search(p))

# 백슬래시 문제
p = re.compile(r'\\section')
m = p.search('\\\\\section')
print('p.search(\'\\\\\\\\\section\')= ', m)