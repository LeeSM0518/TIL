from tkinter import *

window = Tk()
button = Button(window, text="버튼을 클릭하세요")
button.pack()

button['fg'] = 'yellow'
button['bg'] = 'green'



window.mainloop()