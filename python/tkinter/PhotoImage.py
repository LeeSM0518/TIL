import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel
from PyQt5.QtGui import QIcon, QPixmap

class App(QWidget):

    def __init__(self):
        super().__init__()
        self.title = 'PyQt5 image - pythonspot.com'
        self.left = 10
        self.top = 10
        self.width = 640
        self.height = 480
        self.initUI()

    def newLabel(self):
        return QLabel(self)

    def initUI(self):
        self.setWindowTitle(self.title)
        self.setGeometry(self.left, self.top, self.width, self.height)

        labelList = [QLabel(self) for i in range(9)]
        print(labelList)
        # pixmapList = [QPixmap('./images/' + str(i) + '.png') for i in range(9)]
        #
        # for i in range(9):
        #     pixmapList[i].scaledToHeight(200)
        #     pixmapList[i].scaledToWidth(200)
        #     labelList[i].setPixmap(pixmapList[i])

        label = QLabel(self)
        pixmap = QPixmap('./images/4.png')
        pixmap = pixmap.scaledToHeight(200)
        label.setPixmap(pixmap)

        label2 = QLabel(self)
        pixmap2 = QPixmap('./images/5.png')
        pixmap2 = pixmap2.scaledToHeight(200)
        label2.setPixmap(pixmap2)
        label2.move(200, 0)

        # count = 0
        # for i in range(3):
        #     for j in range(3):
        #         labelList[count].move(j * 200, i * 200)
        #         count += 1


        self.resize(600, 600)
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    ex.show()
    sys.exit(app.exec_())