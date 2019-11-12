import turtle

myT = None

myT = turtle.Turtle()
myT.shape('turtle')

for i in [0, 1, 2, 3]:
    myT.forward(200)
    myT.right(90)

myT.done()