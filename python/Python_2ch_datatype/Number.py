#정수형
a = [123, -178, 0]
print('정수형 : ', a)
print()

#실수형
b = [1.2 , -3.45, 4.24E10, 4.24e-10]
print('실수형 : ',b)
print()

#8진수와 16진수 : 0o 또는 0O(숫자0 + 알파벳 소문자 o 또는 대문자 O )로 시작하면 된다.
c = 0o177 #8진수
print('8진수 : ',c)
print()

d = [0x8ff, 0xABC] #16진수 0x로 시작하면 된다.
print('16진수 : ',d)
print()

#복소수
a = [1 + 2j, 3 - 4j] #소문자 j 나 J를 써도 된다.
print('복소수: ',a)
print()

b = 1 + 2j
print('복소수의 실수부분: ',b.real) #복소수의 실수 부분 처리
print()

a= 1 + 2j #복소수의 허수 부분 리턴
print('복소수의 허수 부분: ', a.imag)
print()
a= 1 + 2j #복소수의 켤레복소수를 리턴
print('복소수의 켤레복소수: ',a.conjugate())
print()

 #복소수의 절댓값을 리턴한다.
a = 1 + 2j
print('복소수의 절댓값: ', abs(a))
print()

## ** 연산자는 x**y 처럼 사용되었을 때 x의 y제곱 값을 리턴한다.
x = 3
y = 4
print( 'x={}, y={}, x의 y제곱은 {} '.format(x, y, x**y) )
print()

## 나눗셈 후 나머지를 반환하는 % 연산자
x = 7 % 3
y = 3 % 7
print('7%3 = {}, 3%7 = {}'.format(x,y))
print()

## 나눗셈 후 소수점 아랫자리를 버리는 // 연산자
x = 7 // 4
print('7//4 = {}'.format(x))
