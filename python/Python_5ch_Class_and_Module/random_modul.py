# random 모듈 : 난수 발생시키는 모듈
print('random 모듈\n')
import random

# random.random() : 0.0 에서 1.0 사이의 실수 중에서 난수값을 리턴
print('random.random() = ', random.random())

# random.randint(1,10) : 1에서 10 사이의 정수 중에서 난수값 리턴
print('\nrandom.randint(1,10) = ',random.randint(1,10))

# random example function
print('\nrandom example function')
def random_pop(data):
    number = random.randint(0, len(data) - 1)
    return data.pop(number)

if __name__ == '__main__':
    data = [1,2,3,4,5]
    while data: print(random_pop(data))

# random.choice() : 입력으로 받은 리스트에서 무작위로 하나를 선택하여 리턴.
print('\nrandom.choice() 이용')
def random_pop(data):
    number = random.choice(data)
    data.remove(number)
    return number

if __name__ == '__main__':
    data = [1,2,3,4,5]
    while data : print(random_pop(data))

# random.shuffle() : 리스트의 항목을 무작위로 섞는다.
data = [1,2,3,4,5]
print('\ndata = ', data )
random.shuffle(data)
print('random.shuffle(data) = ', data)

