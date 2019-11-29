import sys
from PyQt5.QtWidgets import *
from PyQt5.QtCore import Qt


class MyApp(QMainWindow):

    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        exitAction = QAction('종료', self)
        exitAction.setShortcut('Ctrl+Q')
        exitAction.setStatusTip('어플을 종료합니다.')
        exitAction.triggered.connect(qApp.quit)

        addAction = QAction('추가', self)
        addAction.setShortcut('Ctrl+N')
        addAction.setStatusTip('새로운 일정을 추가합니다.')
        # addAction.triggered.connect()

        self.statusBar()

        systemMenuBar = self.menuBar()
        systemMenuBar.setNativeMenuBar(False)
        fileMenu = systemMenuBar.addMenu('&System')
        fileMenu.addAction(exitAction)

        menuMenuBar = self.menuBar()
        menuMenuBar.setNativeMenuBar(False)
        menu = systemMenuBar.addMenu('&Menu')
        menu.addAction(addAction)

        label1 = QLabel('label', self)
        label1.setAlignment(Qt.AlignLeft)
        label2 = QLabel('label1', self)
        label2.setAlignment(Qt.AlignCenter)
        label3 = QLabel('label11', self)
        label3.setAlignment(Qt.AlignRight)

        layout = QVBoxLayout()
        layout.addWidget(label1)
        layout.addWidget(label2)
        layout.addWidget(label3)
        self.setLayout(layout)

        self.setWindowTitle('Toolbar')
        self.setGeometry(1000, 1000, 1000, 1000)
        self.center()
        self.show()

    def setLabelColor(self, color, label):
        label.setStyleSheet("color: " + color + ";"
                                                "border-style: solid;"
                                                "border-width: 2px;"
                                                "border-color: #1E90FF;"
                                                "border-radius: 3px")

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MyApp()
    sys.exit(app.exec_())
