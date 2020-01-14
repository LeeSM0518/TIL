from tkinter import *
from tkinter import messagebox

window = Tk()

def fetch():
    messagebox.showinfo('내용출력', '이름' + e1.get() + ',직업' +
                        e2.get() + ',국적' + e3.get())

Label(window, text='이름').grid(row=0)
Label(window, text='직업').grid(row=1)
Label(window, text='국적').grid(row=2)

e1 = Entry(window)
e2 = Entry(window)
e3 = Entry(window)

e1.grid(row=0, column=1)
e2.grid(row=1, column=1)
e3.grid(row=2, column=1)

Button(window, text='Show', command=fetch).grid(row=3, column=0,
                                                sticky=W, pady=4)
Button(window, text='Quit', command=window.quit).grid(row=3, column=1,
                                               sticky=W, pady=4)

mainloop()