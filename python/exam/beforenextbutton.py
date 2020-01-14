from tkinter import *
from time import *

fnameList = ['gifimage.gif', 'gifimage3.gif', 'gifimage4.gif',
             'gifimage5.gif']

photoList = [None] * 9
num = 0

def clickNext():
    global num
    num += 1
    if num >= len(fnameList):
        num = 0
    photo = PhotoImage(file=fnameList[num])
    pLabel.configure(image=photo)
    pLabel.image = photo

def clickPrev():
    global num
    num -= 1
    if num < 0:
        num = len(fnameList) - 1
    photo = PhotoImage(file=fnameList[num])
    pLabel.configure(image=photo)
    pLabel.image = photo

window = Tk()
window.geometry('700x500')
window.title('사진 앨범')

btnPrev = Button(window, text='<< 이전', command=clickPrev)
btnNext = Button(window, text='다음 >>', command=clickNext)

photo = PhotoImage(file=fnameList[0])
pLabel = Label(window, image=photo)

btnPrev.place(x=250, y=10)
btnNext.place(x=400, y=10)
pLabel.place(x=15, y=50)

window.mainloop()