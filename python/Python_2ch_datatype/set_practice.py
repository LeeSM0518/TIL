"""
집합(set) 자료형
특징 :
중복을 허용하지 않는다.
순서가 없다.
"""

s1 = set([1,2,3])
print('s1 = ',s1)
print('s1은 ',type(s1))

l1 = list(s1)
print('s1을 list로 변환 후 l1 = {} , l1 은 {}.'.format(l1,type(l1)))

t1 = tuple(s1)
print('s1을 tuple로 변환 후 t1 = {}, t1 은 {}.'.format(t1,type(t1)))

#교집합, 합집합, 차집합
s1 = set([1,2,3,4,5])
s2 = set([3,4,5,6,7])
print('s1 = {} , s2 = {}.'.format(s1,s2))
print('s1 과 s2의 교집합 = {}'.format(s1&s2))
print('s1 과 s2의 합집합 = {}'.format(s1|s2))
print('s1 과 s2의 차집합 = {}'.format(s1-s2))
print()

#관련 함수들
s1 = set([1,2,3])
print('s1 = {}'.format(s1))
s1.add(4)
print('4를 추가한 s1 = {}'.format(s1))
s1.update([5,6,7])
print('여러 값을 추가한 s1 = {}'.format(s1))
s1.remove(7)
print('특정 값을 제거한 s1 = {}'.format(s1))