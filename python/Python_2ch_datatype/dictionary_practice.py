#dictionry 만들기
a = {'name' : 'min', 'phone' : '01071889608', 'birth' : '0518'}

#dict 나열
for key, value in a.items():
    print('{} : {}\n'.format(key,value))

#key 유무 확인
print('name이 a에 들어잇는가?',('name' in a))