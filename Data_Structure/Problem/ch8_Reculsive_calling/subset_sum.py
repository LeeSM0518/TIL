def subset(data, position, n, depth, s):
    sum = 0
    if depth == 0:
        global count
        count = 0
    if n == depth :         # 재귀에서 나오기 위한 조건문
        for i in range(n):
            if position[i] == 1 :
                sum += data[i]
        if sum == s:
            count += 1
        return
    position[depth] = 1
    subset(data, position, n, depth + 1, s)
    position[depth] = 0
    subset(data, position, n, depth + 1, s)

data = []
position = []

num = int(input('정수의 개수 : '))
integer = int(input('원소들의 합이 될 정수 :'))

for i in range(num):
    x = int(input('원소 : '))
    data.append(x)

for i in range(num):
    position.append(0)

subset(data, position, num, 0, integer)

if (integer == 0):
    count -= 1

print('{}이/가 되는 부분집합의 개수는 {} 이다.'.format(integer,count))