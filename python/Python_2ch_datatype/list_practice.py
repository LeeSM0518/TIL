# 값 수정
a = [1 ,2 ,3]
print('a = {}'.format(a))

a[1:3] = [4,5] # [처음값 : 미만값]
print('값 수정한 a = {}\n'.format(a))

# 리스트 삭제
a = ['a','b','c']
print('a= ',a)
del a[1:3]
print('리스트 삭제한 a = {}\n'.format(a))

# 리스트 추가
a = [1,2,3]
print('a = {}'.format(a))
a.append([4,5])
print('리스트를 추가한 a = {}'.format(a))
print('a[3] = ',a[3])
print()

# 리스트 정렬
a = [3,1,2]
print('a = {}'.format(a))
a.sort()
print('정렬한 a = {}'.format(a))
a.reverse()
print('뒤집은 a = {}\n'.format(a))

# 리스트 삽입
a = [1,2,3]
print('a = {}'.format(a))
a.insert(0,4)
print('삽입한 a = {}\n'.format(a))

# 리스트 요소 제거
a = [1,2,3,1,2,3]
print('a = {}'.format(a))
a.remove(3)
print('3을 삭제한 a = {}'.format(a))
a.remove(3)
print('3을 한번더 삭제 a = {}\n'.format(a))

# 리스트 요소 개수 세기
a = [1,1,1]
print('a = {} 1의 개수는 {} 이다.\n'.format(a,a.count(1)))

# 리스트 확장
a = [1,2,3]
print('a = {}'.format(a))
a.extend([4,5])
print('확장한 a = {}\n'.format(a))

# 리스트의 마지막 요소 꺼냄
a = [1,2,3]
print('a가 참인 동안 a의 마지막 요소를 꺼냄')
while a:    # a가 참인 동안
    print(a.pop())

# 중첩된 리스트에서 슬라이싱
print('리스트 안에 있는 리스트의 요소를 지움.')
a = [1,2,[3,4],5]
print('a = ',a)
del a[2][0]
print('a = ',a)

# 리스트 수정할 때 주의할 점
# a[1:2] = [ 'a', 'b', 'c' ] 가 아니라 a[1] = ['a','b','c'] 로 수정하면 전혀 다르게 수정이 된다.

