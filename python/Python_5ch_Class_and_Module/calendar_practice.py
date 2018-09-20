# calendar : 달력을 볼 수 있게 해주는 모듈 이다.
import calendar
print('calendar 모듈')
# calendar.calendar(연도) : 그 해의 전체 달력을 볼 수 있다.
print('\ncalendar.calendar(2018) = \n', calendar.calendar(2018) )

# calendar.prcal(연도) : 위와 같다.
print('\ncalendar.prcal(2015) = \n' )
calendar.prcal(2015)

# calendar.prmonth(2015, 12) : 해당되는 연도의 월만 보여준다.
print('\ncalendar.prmonth(2019, 5) = \n' )
calendar.prmonth(2019, 5)

# calendar.weekday(연도, 월, 일) : 그 날짜에 해당하는 요일 정보 리턴
print('\ncalenar.weekday(2015, 12, 31) = ',calendar.weekday(2015, 12, 31))

# calendar.monthrange(연도, 월)
print('\ncalendar.monthrange(2015, 12) = ',calendar.monthrange(2015, 12))