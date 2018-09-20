# 문자열 1번
print('문자열 1번 문제')
pin = '881120-1068234'

yyyymmdd = pin[0:6]
num = pin[7:16]

print(yyyymmdd)
print(num,'\n')

# 문자열 2번
print('문자열 2번 문제')
pin ='881120-1068234'
print(pin[7],'\n')

# 리스트
print('리스트 1번 문제')
a = [1,3,5,4,2]
a.sort()
a.reverse()
print(a,'\n')

print('리스트 2번 문제')
a = ['Life', 'is', 'too', 'short']
result = ' '.join(a)
print(result,'\n')

# 튜플
print('튜플 1번 문제')
a = (1,2,3)
a = a + (4,)
print(a,'\n')

# 딕셔너리
print('딕셔너리 1번 문제')
c = { 'A': '90', 'B': '80' , 'C': '70' }
result = c['B']
del c['B']
print(c)
print(result,'\n')

#집합
print('집합 1번 문제')
a = [1,1,1,2,2,3,3,3,4,4,5]
aSet = set(a)
b = list(aSet)
print(b,'\n')

#변수
print('변수 1번 문제')
a = b = [1,2,3]
a[1] = 4
print(b,'\n') # a가 b를 가르키게 되고 b가 [1,2,3]을 가리키게 되는데 여기서 a가 변경되면 b도 같이 변경되게 된다.

# a.keys() 를 하여 dict_keys(['name', 'phone', 'birth']) 를 얻을 수 있는데 이것은 리스트 고유의 함수인 append, insert, pop등의 함수를 수행할 수 는 없다.
