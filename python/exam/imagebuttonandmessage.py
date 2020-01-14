from tkinter import *
from tkinter import messagebox

def myFunc():
    messagebox.showinfo('강아지 버튼', '강아지가 귀엽쥬?')

window = Tk()

photo = PhotoImage(file='gifimage.gif')
button = Button(window, image=photo, command=myFunc)
button.pack()

window.mainloop()