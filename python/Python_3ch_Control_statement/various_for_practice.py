# 다양한 for문의 사용
print('다양한 for문의 사용')
a = [(1,2),(3,4),(5,6)]
print('a = {}'.format(a))
for(first, last) in a:
    print(first + last)
print()

# for문과 continue
print('for문과 continue')
marks = [90, 25, 67, 45, 80]
number = 0
for mark in marks:
    number = number + 1
    if mark < 60: continue
    print('%d번 학생 축하합니다. 합격입니다!'%number)
print()

# for문과 range 함수
print('for문과 range 함수')
sum = 0
print('1부터 10까지의 합')
for i in range(1,11):
    sum = sum + i
    print(sum)
print()

# for과 range를 이용한 구구단
print('for과 range를 이용한 구구단')
for i in range(2,10):
    for j in range(1,10):
        print(i*j,end=" ") # end=" " 를 사용하여 print문이 끝나면 줄이 바뀌는 것을 보완해주었다.
    print()
print()

# 리스트 안에 for문 포함하기
print('리스트 안에 for문 포함하기')
a = [1,2,3,4]
result = []
for num in a:
    result.append(num*3)
    print(result)
print()
result2 = [ num * 3 for num in a]
print(result2)

result3 = [num * 3 for num in a if num % 2 == 0]
print(result3)

result4 = [x*y for x in range(2,10) for y in range(1,10)]
print(result4)