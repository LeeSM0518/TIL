from tkinter import *

window = Tk()
window.geometry('210x210')

photo = PhotoImage(file='gifimage.gif')

xPos = 0
yPos = 0

for i in range(0, 3):
    for k in range(0, 3):
        btn = Button(window, image=photo)
        btn.place(x=xPos, y=yPos)
        xPos += 70
    xPos = 0
    yPos += 70

window.mainloop()
