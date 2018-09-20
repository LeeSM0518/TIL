# 조건에 맞지 않는 경우 맨 처음으로 돌아가기
a = 0
while a < 10:
    a = a + 1
    if a % 2 == 0: continue # a % 2 == 0 일 경우 맨 처음으로 돌아가 진행한다.
    print(a)
# break문
coffee = 10
while True:
    money = int(input('돈을 넣어 주세요:'))
    if money == 300:
        print('커피를 줍니다.')
        coffee = coffee - 1
    elif money > 300:
        print('거스름돈 %d를 주고 커피를 줍니다.' %(money - 300))
        coffee = coffee - 1
    else:
        print('돈을 다시 돌려주고 커피를 주지 않습니다.')
        print('남은 커피의 양은 %d개 입니다.'%coffee)
    if not coffee:
        print('커피가 다 떨어졌습니다. 판매를 중지합니다.')
        break