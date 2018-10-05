# 한 줄에 결과값 출력하기
print('한 줄에 결과값 출력 : ')
for i in range(10):
    print(i ,end=' ')
print()

# 구구단
print('구구단을 출력할 숫자를 입력하세요(2~9) :')
n = input()
for i in range(1,10):
    print(n * i)