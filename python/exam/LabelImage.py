from tkinter import *
from PIL import ImageTk, Image

window = Tk()

photo = PhotoImage(file='gifimage.gif')
label = Label(window, image=photo)
label.pack()

window.mainloop()