# 외장 함수

# sys 모듈 : 변수들과 함수들을 직접 제어할 수 있게 해주는 모듈

# sys.argv : 명령 행에서 인수 전달하기

# sys.exit : 강제로 스크립트 종료하기

# sys.path : 자신이 만든 모듈 불러와 사용하기
import sys
print('내가 만든 모듈 불러오기 = ', sys.path)
print('모듈 디렉터리 추가 = sys.path.append(\"C:/Python/Mymodules\") \n')

# pickle : 객체의 형태를 그대로 유지하면서 파일에 저장하고 불러올 수 있게 하는 모듈.
import pickle
f = open("test.txt",'wb') # test.txt 파일의 글자가 깨지는 이유 물어보기 !!
data = {1:'python',2:'you need'}
pickle.dump(data,f)
f.close()
print('pickle을 이용해 test.txt 를 읽음')
f = open("test.txt",'rb')
data = pickle.load(f)
print(data)
print()

# OS 모듈 : 환경 변수나 디렉터리, 파일 등의 OS 자원을 제어할 수 있게 해주는 모듈.
print('OS 모듈')

# os.environ : 내 시스템의 환경 변수값을 알고 싶을 때
import os
print('내 시스템의 환경 변수값들 os.environ = ', os.environ)
print('필자 시스템의 PAHT환경 변수 os.environ[\'PATH\'] = ',os.environ['PATH'])

# os.chdir : 디렉터리 위치 변경하기

# os.getcwd : 디렉터리 위치 리턴받기
print('현재 디렉터리 위치 리턴 os.getcwd() = ',os.getcwd())

# os.system : 시스템 명령어 호출하기
print('시스템 명령어 호출 os.system("dir") ')

# os.popen : 실행한 시스템 명령어의 결과값 리턴받기
print('\n dir 시스템 명령어 리턴 \n')
f = os.popen("dir")
print(f.read())

"""
기타 유용한 os 관련 함수 : 
os.mkdir    :   디렉터리 생성
os.rmdir    :   디렉터리 삭제
os.unlink   :   파일 삭제
os.rename(src, dst)     :   src라는 이름의 파일을 dst라는 이름으로 바꿈
"""

# shutil.copy( src, dst ) : 파일 복사하기
print('\nshutil 함수')
import shutil
f = open('test2.txt','w')
data = "test"
f.write(data)
f.close()

shutil.copy('test2.txt','test_copy.txt')

f = open('test_copy.txt','r')
data = f.readline()
print('test_copy.txt return\n',data)
f.close()

# glob(pathname) : 디렉터리에 있는 파일들을 리스트로 만들기
print('\nglob 함수')
import glob
list = glob.glob("C:/Users/lenovo/Desktop/wisoft/seminar/python/Python_5ch/m*")
print('glob한 값을 넣은 list = ', list)

# tempfile.mktemp() : 파일을 임시로 만들어서 사용할 대 유용하다.
print('\ntempfile 함수')
import tempfile
filename = tempfile.mkdtemp()
print('임시로 만든 파일 = ',filename)
os.rmdir(filename)
list = glob.glob("C:/Users/lenovo/AppData/Local/Temp/tmp*")
print('임시로 만든 파일 삭제 확인 = ', list)

# tempfile.TemporaryFile() : 임시 저장 공간으로 사용될 파일 객체를 리턴.
f = tempfile.TemporaryFile()
print('임시 파일 = ', f)
f.close()