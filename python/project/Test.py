import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout, QDesktopWidget
from PyQt5.QtCore import Qt


class MyApp(QWidget):

    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):
        label1 = QLabel('First Label', self)
        label1.setAlignment(Qt.AlignLeft)

        label2 = QLabel('Second Label', self)
        label2.setAlignment(Qt.AlignLeft)

        font1 = label1.font()
        font1.setPointSize(20)

        font2 = label2.font()
        font2.setFamily('Times New Roman')
        font2.setBold(True)

        label1.setFont(font1)
        self.setLabelColor('blue', label1)

        label2.setFont(font2)

        layout = QVBoxLayout()
        layout.addWidget(label1)
        layout.addWidget(label2)

        self.setLayout(layout)

        self.setWindowTitle('QLabel')
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
