"""
변수란?    변수는 객체를 가리키는 것이다.

객체란?    객체란 우리가 지금껏 보아 왔던 자료형을 포함해 '파이썬에서 사용되는 모든 것' 이다.

a = 3 ?     a는 변수의 이름이며, 3이라는 정수형 객체가 저장된 메모리 위치를 가르키게 된다.
            // 변수 a는 객체가 저장된 메모리의 위치를 가리키는 레퍼런스 라고도 한다.


"""

# 변수를 만드는 여러 가지 방법
a, b = ('python', 'life')
print(a,b)

# 값 바꾸기
print()
print('a = {} b = {}'.format(a,b))
a, b = b, a
print('a = {} b = {}\n'.format(a,b))

#[:] 이용
a = [1,2,3]
b = []
print('a = {}, b = {}'.format(a,b))
b = a[:]
print('값 복사 후 a = {} , b = {}'.format(a,b))

# copy 모듈 이용
import copy
a = [1,2,3]
b = []

print('\na = {}\nb = {}\n'.format(a,b))
