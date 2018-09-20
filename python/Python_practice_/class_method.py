class Human():
	"""인간"""
	def __init__(self, name, weight):
		"""초기화 함수"""
		self.name = name
		self.weight = weight
		
	def __str__(self):
		"""문자열화 함수"""
		return "{}의 몸무게는 {}kg 이다" .format(self.name, self.weight)
		
	def eat(self):
		self.weight += 0.1
		print("{}가 먹어서 {}kg이 되었습니다." .format(self.name, self.weight))
		
	def walk(self):
		self.weight -= 0.1
		print("{}가 걸어서 {}kg이 되었습니다." .format(self.name, self.weight))
		
person = Human('상민', 60.5)
print(person)