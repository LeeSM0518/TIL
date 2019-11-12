import turtle
import threading, time
import random
import keyboard

t = turtle.Pen('turtle')
# p = turtle.Pen('turtle')
# p.pencolor("blue")
t.pencolor("pink")

# def turtleRandomMove():
#     while True:
#         try:
#             print('thread')
#             p.left(int(random.randrange(1, 10)))
#             p.right(int(random.randrange(1, 10)))
#             p.forward(int(random.randrange(1, 10)))
#             p.back(int(random.randrange(1, 10)))
#         except:
#             return 0
#
# thread = threading.Thread(target=turtleRandomMove)
# thread.start()
count = 0
while True:
    # print(t.xcor())
    # print(t.ycor())
    try:
        count += 1
        if (count > 100) : count = 0
        if count % 10 == 0 and keyboard.is_pressed('a'):
            t.left(10)
        elif count % 15 == 0 and keyboard.is_pressed('d'):
            t.right(10)
        elif count % 20 == 0 and keyboard.is_pressed('s'):
            t.back(10)
        elif count % 25 == 0 and keyboard.is_pressed('w'):
            t.forward(10)
        elif keyboard.is_pressed('q'):
            break
    except:
        break
