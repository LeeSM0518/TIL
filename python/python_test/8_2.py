def f_to_c(F):
    C = 5.0 / 9.0 * (F - 32.0)
    return C

F = float(input('화씨 온도를 입력하시오: '))

print('섭씨 온도는 {}도 입니다.' .format(f_to_c(F)))

