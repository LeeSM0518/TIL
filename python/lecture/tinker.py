from tkinter import *
from tkinter.filedialog import *

def func_open():
    filename = askopenfilename(parent = window, filetypes = (("GIF 파일", "*.gif"),
                                                             ("모든 파일", "*.*")))
    photo = PhotoImage(file = filename)
    pLabel.configure(image = photo)
    pLabel.image = photo

def func_exit():
    window.quit()
    window.destroy()
window = Tk()
window.geometry("400x400")

window.title("명화 감상하기")

photo = PhotoImage()
pLabel = Label(window, image = photo)
pLabel.pack(expand =1, anchor = CENTER)


mainMenu = Menu(window)
fileMenu = Menu(mainMenu)
window.config(menu = mainMenu)
mainMenu.add_cascade(label = "파일", menu = fileMenu)

fileMenu.add_command(label= "파일 열기", command = func_open)
fileMenu.add_separator()
fileMenu.add_command(label="프로그램 종료", command = func_exit)

window.mainloop()