from tkinter import *

class App:
    def __init__(self):
        window = Tk()
        helloB = Button(window, text="Hello", command=self.hello, fg='red')
        helloB.pack(side=LEFT)
        quitB = Button(window, text="Quit", command=self.quit)
        quitB.pack(side=LEFT)
        window.mainloop()

    def hello(self):
        print('Hello 버튼이 클릭됨')

    def quit(self):
        print('Quit 버튼이 클릭됨')

App()