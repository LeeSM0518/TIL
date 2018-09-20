class Animal():
  def __init__(self, name):
    self.name = name

  def greet(self):
    print('{}가 인사한다'.format(self.name))

class Human(Animal):
  def __init__(self, name, hand):
    super().__init__(name)
    self.hand = hand

  def wave(self):
    print('{}을 흔들면서'.format(self.hand))

  def greet(self):
    self.wave()
    super().greet()

person = Human('철수', '오른손')
person.greet()