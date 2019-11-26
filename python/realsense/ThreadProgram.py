import random


class Depth_frame:
    def get_distance(self, x, y):
        return random.randint(1, 100)


col = [x for x in range(32, 609, 64)]
row = [y for y in range(18, 342, 36)]

depth_frame = Depth_frame()

for i in range(10):
    arr = []
    for x in col:
        for y in row:
            value = depth_frame.get_distance(x, y)
            print value
            if value < 30:
                if arr.count(x) == 0:
                    arr.append(x)

    print arr
