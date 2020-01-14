from tkinter import *

window = Tk()

btnList = [None] * 3

for i in range(0, 3):
    btnList[i] = Button(window, text='버튼' + str(i + 1))

for btn in btnList:
    btn.pack(side=TOP) # or BOTTOM

window.mainloop()