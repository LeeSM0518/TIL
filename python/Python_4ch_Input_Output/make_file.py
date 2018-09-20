# 파일 생성하기
f = open('새파일.txt','w') # 파일 객체 = open(파일이름, 파일 열기 모드)
for i in range(1, 11):
    data = '%d rine.\n' %i
    f.write(data)
f.close()

# 프로그램의 외부에 저장된 파일을 읽는 여러 가지 방법
# readline() 함수 이용하기

f = open('새파일.txt','r')
line = f.readline()
print('한 줄만 출력 : ')
print(line)
print()
f.close()

print('여러줄 출력: ')
f = open('새파일.txt','r')
while True:
    line = f.readline()
    if not line : break
    print(line)
f.close()
print()

# readlines() 함수 이용하기
print('readlines() 함수 이용')
f = open('새파일.txt','r')
lines = f.readlines() # readlines() 함수는 파일의 모든 라인을 읽어서 각각의 줄을 요소로 갖는 리스트로 리턴한다.
for line in lines:
    print(line)
f.close()

# read() 함수 이용하기
print('read() 함수 이용')
f = open('새파일.txt','r')
data = f.read()
print(data)
f.close()
print()

# 파일에 새로운 내용 추가하기
print('내용 추가')
f = open('새파일.txt','a')
for i in range(11,20):
    data = '%d line.'%i
    f.write(data)
f.close()
print()

# with문과 함께 사용하기
f = open('foo.txt','w')
f.write('Life is too short,you need python\n')
f.close()
with open('foo.txt','a') as f:
    f.write('Life is too long')

