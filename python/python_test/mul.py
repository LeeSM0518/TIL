num = int(input('구구단에 넣을 숫자를 입력해주세요: '))
i=1

for i in range(10):
    print('{} * {} = {}'.format(num, i, num*i))