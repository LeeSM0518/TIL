from tkinter import *
window = Tk()

button = Button(window, text='파이썬 종료', fg='red', command=quit)
button.pack()

window.mainloop()