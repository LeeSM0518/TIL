from my_exception_plus import UnexceptedRSPValue 

value = '가'

try:
  if value not in ['가위','바위','보']:
    raise UnexceptedRSPValue
except UnexceptedRSPValue:
  print('에러가 발생했습니다.')