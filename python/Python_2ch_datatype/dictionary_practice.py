#dictionry 만들기
a = {'name' : 'min', 'phone' : '01071889608', 'birth' : '0518'}

#dict 나열
for key, value in a.items():
    print('{} : {}\n'.format(key,value))

#key 유무 확인
print('name이 a에 들어잇는가?',('name' in a))

# dictionry , id ( Data_Structure 연습 )
print('\nid 확인')
pLink = { 'CurrentId': 0 , 'NextId' : 0}
print(pLink['CurrentId'])
pLink['CurrentId'] = id(pLink)
print(pLink['CurrentId'])
print(type(pLink['CurrentId']))
pLink['CurrentId'] = pLink
print(pLink['CurrentId'])
print(id(pLink['CurrentId']))
print(type(pLink['CurrentId']))

# clear
print('\nclear 함수')
print(pLink)
pLink.clear()
print(pLink)