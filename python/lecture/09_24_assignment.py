money, c50000, c10000, c5000, c1000 = 0, 0, 0, 0, 0

money = int(input("고환할 돈은 얼마?"))

c50000 = money // 50000
money %= 50000

c10000 = money // 10000
money %= 10000

c5000 = money // 5000
money %= 5000

c1000 = money // 1000
money %= 1000

print('\n 500원짜리 => %d개' % c50000)
print(' 500원짜리 => %d개' % c10000)
print(' 500원짜리 => %d개' % c5000)
print(' 500원짜리 => %d개' % c1000)
print(' 바꾸지 못한 잔돈 => %d개' %money)