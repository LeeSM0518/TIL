from tkinter import *
from tkinter.simpledialog import *

window = Tk()
window.geometry('500x500')

label1 = Label(window, text="직사각형의 넓이는")
label1.pack()

width = askinteger('직사각형', '가로 길이를 입력하세요', minvalue=100, maxvalue=400)
height = askinteger('직사각형', '세로 길이를 입력하세요', minvalue=100, maxvalue=400)

label1.configure(text='직사각형의 넓이는 ' + str(width * height) + ' 입니다.')

w = Canvas(window, width=width, height=height)
w.pack()
w.create_rectangle(20, 20, 20 + width, 20 + height, fill='blue')
window.mainloop()