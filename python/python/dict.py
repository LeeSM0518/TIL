import random

wintable = {
	'가위' : '보' ,
	'바위' : '가위' ,
	'보' : '바위'
	}

list = ['가위', '바위' , '보' ]	

me = random.choice(list)
print('나는 {} 이다.' .format(me))
you = random.choice(list)
print('너는 {} 이다.' .format(you))
	
def rsp(mine, yours):
	if mine == yours:
		return 'draw'
	elif wintable[mine] == yours:
		return 'win'
	else:
		return 'lose'
	
result = rsp(me, you)

messages = {
	'win' : '이겼다!',
	'draw' : '비겼다!',
	'lose' : '졌다!'
	}

print(messages[result])