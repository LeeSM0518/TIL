from tkinter import *
window = Tk()

label = Label(window, text="안녕하세요!")
label.pack()

button = Button(window, text="tkinter로 버튼을 쉽게 만들 수 있다.")
button.pack()

window.mainloop()