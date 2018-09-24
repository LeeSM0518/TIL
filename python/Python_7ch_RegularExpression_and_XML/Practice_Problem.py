# 연습문제

# Q2
import re
p = re.compile('[a-z]+')
m = p.search('3 python')
sum = m.start() + m.end()
print(m.start())
print(m.end())
print(m.group())
print(sum)

# Q3
print('\nQ3')
import re

data ="""
park 010-9999-9988
kim 010-9909-7789
lee 010-8789-7768
"""

def change_phoneNum(data):
    p = re.compile('(\w+\s+\d+[-]\d+[-])(\d+)',re.MULTILINE)
    print(p.sub('\g<1>####',data))

change_phoneNum(data)

# Q4
print('\nQ4')
def imail_check(data):
    p = re.compile('.*[@].*(?=[.]com$|[.]net$)',re.MULTILINE)
    print(p.findall(data))

data ="""
park@naver.com
kim@daum.net
lee@myhome.co.kr
"""

imail_check(data)

