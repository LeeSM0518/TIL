# sys 모듈로 입력 인수 주기
import sys

args = sys.argv[1:]
for i in args:
    print(i)

# sys 모듈의 argv 는 명령창에서 입력한 인수들을 의미한다.
# 즉 , sys1.py aaa bbb ccc  << 와 같이 입력했다면 argc[0]는 파일이름인 sys1.py가 되고 argv[1] 부터는 뒤에 따라오는
# 인수들이 차례로 argv의 요소가 된다.
# sys1.py << argv[0] , aaa << argv[1] , bbb << argv[2] , ccc << argv[3]
