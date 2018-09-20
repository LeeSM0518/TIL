# 오류 예외 처리 기법
"""
1 번째 방법
try:
    ...
except :
    ...

2 번째 방법
try:
    ...
except 발생 오류 :
    ...

3 번째 방법
try:
    ...
except 발생오류 as 오류 메시지 변수:
    ...
"""
print('에러 발생 : ')
try:
    4/0
except ZeroDivisionError as e:
    print(e)
print()

# try .. else
print('try .. else')
try:
    f = open('나없음','r')
except FileNotFoundError as e:
    print(str(e))
else:
    data = f.read()
    f.close()

f=open('foo.txt','w')
f.write('write')
f.close()

print()

print('foo파일 생성 및 읽기')
try:
    f = open('foo.txt','r')
except FileNotFoundError as e:
    print(str(e))
else:
    data = f.read()
    print(data)
    f.close()
    print()

# try .. finally
print('try .. finally')
f = open('foo.txt','w')
try:
    # 무언가를 수행한다.
    f.write('finally try')
finally:
    f=  open('foo.txt','r')
    data = f.readlines()
    print(data)
    f.close()
print()

# 오류 회피하기
print('오류 회피하기')
try:
    f = open('없는파일','r')
except FileNotFoundError:
    pass
    print('pass')

# 오류 일부러 발생시키기
class Bird:
    def fly(self):
        raise NotImplementedError

class Eagle(Bird):
    def fly(self):
        print('very fast')

eagle = Eagle()
eagle.fly()
