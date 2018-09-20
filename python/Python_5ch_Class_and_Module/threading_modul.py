# threading 모듈 : 한 프로세스 내에서 2가지 도는 그 이상의 일을 동시에 수행하게 할 수 있다.
import threading
import time

def say(msg):
    while True:
        time.sleep(1)
        print(msg)

for msg in ['you','need','python']:
    t = threading.Thread(target=say, args =(msg,)) #
    t.daemon = True
    t.start()

for i in range(15):
    time.sleep(0.1)
    print(i)