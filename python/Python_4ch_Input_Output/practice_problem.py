# 함수 1번
print('함수 1번')
''' 
직접 코딩한 피보나치
class fiv():
    #data

count = fiv()
count.list = [0]
test = fiv()
test.list =[]

def fib(n):
    result = 0
    if n==0 :
        if 0 not in test.list:
            test.list.append(0)
        return 0
    if n==1 :
        count.list[0] = test.list.count(1)
        if count.list[0] <= 1 :
            test.list.append(1)
        return 1
    if n>=2 :
        result = fib(n-2) + fib(n-1)
        count.list[0] = test.list.count(n)
        if count.list[0] <= 1 :
            test.list.append(result)
        return result

fib(7)
test.list.sort()
print('last list', test.list)
'''

# 답지 참고한 피보나치
def fib(n):
    if n==0: return 0
    if n==1: return 1
    return fib(n-2) + fib(n-1)

for i in range(10):
    print(fib(i),end=' ')

# 파일 읽고 쓰기 1번
f = open('sample.txt','w')
data = """70
60
55
75
95
90
80
80
85
100"""
f.write(data)
f.close()

sum = 0

f = open('sample.txt','r')
lines = f.readlines()
for line in lines:
    sum += int(line)
average = sum / len(lines)
with open('result.txt','w') as f:
    data = 'sum = {} and average = {}'.format(sum, average)
    f.write(data)
    f.close()
