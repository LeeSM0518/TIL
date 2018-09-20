class Car():
    
    def run(self):
        print("차가 달립니다.")

# 아래에서 Car를 상속받는 Truck이라는 클래스를 만들고, load라는 메소드를 만들어 보세요.
# load메소드에서는 "짐을 실었습니다."라고 출력하면 됩니다.

class Truck(Car):
    def load(self):
        print("짐을 실었습니다.")

truck = Truck()
truck.run()
truck.load()
