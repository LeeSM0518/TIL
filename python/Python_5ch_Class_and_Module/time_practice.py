# time 모듈 : 시간 관련된 모듈
print('\ntime 모듈')

# time.time : 현재 시간을 실수 형태로 리턴
print('\ntime.time()')
import time
print(time.time())

# time.localtime : 연도, 월, 일, 시, 분, 초 .. 의 형태로 리턴
print('\ntime.localtime()')
print('time.location(time.time()) = ',time.localtime(time.time()))

# time.asctime : 날짜와 시간을 알아보기 쉬운 형태로 리턴
print('\ntime.asctime(time.localtime(time.time())) = ', time.asctime(time.localtime(time.time())))

# time.ctime : time.asctime(time.localtime(time.time()))) 은 time.ctime을 이용해 간단하게 표시할 수 있다.
print('\ntime.ctime = ',time.ctime())

# time.strftime('출력할 형식 포맷 코드', time.localtime(time.time()))
print('\ntime.strftime(\'%x\'), time.localtime(time.time())) = ', time.strftime('%x', time.localtime(time.time())))
print('\ntime.strftime(\'%c\'), time.localtime(time.time())) = ', time.strftime('%c', time.localtime(time.time())))

# time.sleep : 일정한 시간 간격을 두고 루프 실행
print('\nsleep 함수')
for i in range(3):
    print(i)
    time.sleep(1)