

def hanoi_tower(n, fr, temp, to):
    if n == 1 :
        print("원판1을 {}에서 {}로 옮겼습니다.".format(fr, to))
    else :
        hanoi_tower(n-1, fr, to, temp)
        print('원판{}를 {}에서 {}로 옮겼습니다.'.format(n,fr,to))
        hanoi_tower(n-1, temp, fr, to)

fr = 'A'
temp = 'B'
to = 'C'

hanoi_tower(4,fr,temp,to)

