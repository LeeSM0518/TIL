import random

list = [0] * 6

for i in range(1000):
    list[random.randint(0, 5)] += 1

for i in range(6):
    print('주사위가 {} 인 경우는 {} 번' .format(i+1, list[i]))