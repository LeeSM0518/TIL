from tkinter import *

window = Tk()
window.geometry('400x400')

btnList = [None] * 3

for i in range(0, 3):
    btnList[i] = Button(window, text='버튼' + str(i + 1))

for btn in btnList:
    btn.pack(side=TOP, fill=X, ipadx=10, ipady=10) # or BOTTOM

window.mainloop()