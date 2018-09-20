# 튜플은 값의 삭제, 수정이 불가능하다.
a = (1,2,3)
print(a,type(a))

b = (4,5)
print(b,type(b))

c = a + b
c = c + (6,)
print(c)