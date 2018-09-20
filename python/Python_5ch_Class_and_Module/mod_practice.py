# 모듈 만들고 불러 보기
print('1. 모듈 만들고 불러 보기')
import mod1
print('mod1.sum(3,4) = {}'.format(mod1.sum(3,4)))

print('\nmod1.safe_sum(1,\'a\') =')
mod1.safe_sum(1,'a')

"""
mod1.sum, mod1.safe_sum 처럼 쓰지 않고 그냥 sum, safe_sum 처럼 함수를 쓰고 싶은 경우도 있을 것이다.
이럴 때는 'from 모듈이름 import 모듈함수' 를 사용하면 된다.

ex)
from mod1 import sum
sum(3, 4)
>> 7
"""
print()
# if __name__ == "__main__" : 의 의미
# mod1.py 참고

import mod2 # __name__ == "__main__" 이 거짓이 되므로 아무런 값도 출력되지 않는다.
print('mod2.PI = {}'.format(mod2.PI))
a = mod2.Math()
print(a.solv(2))
print(mod2.sum(mod2.PI,4.4))