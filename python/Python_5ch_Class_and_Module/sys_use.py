# sys를 이용해서 파이썬 라이브러리가 설치되어 있는 디렉토리를 확인할 수 있다.
import sys

print(sys.path)

sys.path.append("C:/Users/lenovo/Desktop/wisoft/seminar/python/Python_5ch")
# 특정한 디렉토리에 있는 모듈을 불러와서 사용하고 싶을 때 사용할 수 있는 것이 바로 sys.path.append(모듈을 저장한 디렉토리) 이다.

import mod2
print(mod2.sum(3,4))