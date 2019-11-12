from tkinter import *

def callback():
    button['text'] = '버튼이 클릭되었음!'

window = Tk()
button = Button(window, text='클릭', command=callback)
button.pack(side=LEFT)

window.mainloop()