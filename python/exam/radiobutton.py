from tkinter import *

window = Tk()

def myFunc():
    if var.get() == 1:
        label1.configure(text='파이썬')
    elif var.get() == 2:
        label1.configure(text='c++')
    else:
        label1.configure(text='java')

var = IntVar()
rb1 = Radiobutton(window, text='파이썬', variable=var, value=1, command=myFunc)
rb2 = Radiobutton(window, text='c++', variable=var, value=2, command=myFunc)
rb3 = Radiobutton(window, text='java', variable=var, value=3, command=myFunc)

label1 = Label(window, text='선택한 언어 : ', fg='red')

rb1.pack()
rb2.pack()
rb3.pack()
label1.pack()

window.mainloop()