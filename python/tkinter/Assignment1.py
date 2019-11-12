import sys
from PyQt5.QtWidgets import (QApplication, QWidget, QGridLayout, QLabel, QLineEdit, QTextEdit)
from PyQt5.QtGui import QPixmap

class MyApp(QWidget):

    imageList = []
    labelList = []

    def __init__(self):
        super().__init__()

        self.initUI()

    def makeImage(self, image):
        pixmap = QPixmap(self.imageRes(image))
        pixmap.scaledToWidth(300)
        pixmap.scaledToHeight(300)
        return pixmap

    def imageRes(selft, image):
        return './images/' + image + '.png'

    def labelSetImage(self, image):
        label = QLabel(self)
        label.setPixmap(image)
        return label

    def initUI(self):
        self.imageList = [self.makeImage(str(i)) for i in range(1, 10)]
        self.labelList = [self.labelSetImage(self.imageList[i]) for i in range(9)]

        for label in self.labelList:
            label.resize(300, 300)

        for i in range(1, 4):
            for j in range(1, 4):
                self.labelList[0].move(500, 500)

        print(self.imageList)
        print(self.labelList)

        index = 0
        for i in range(3):
            for j in range(3):
                QLabel(self.labelList[index])
                index += 1

        self.setWindowTitle('QGridLayout')
        size = 500
        self.resize(size, size)
        self.show()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyApp()
    sys.exit(app.exec_())




