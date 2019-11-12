from tkinter import *

window = Tk()
b1 = Button(window, text="첫번째 버튼")
b2 = Button(window, text="두번째 버튼")
b1.pack(side=LEFT, padx=10)
b2.pack(side=LEFT, padx=10)

window.mainloop()