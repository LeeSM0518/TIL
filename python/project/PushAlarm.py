from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QDialog, QLabel, QLineEdit, QPushButton, QGridLayout, QComboBox, QDesktopWidget


class PushAlarm(QDialog):

    def __init__(self, work):
        super().__init__()
        self.work = work
        self.stopFlag = False
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('알림 메시지')
        self.setWindowIcon(QIcon('image/alarm.png'))

        messageLabel = QLabel(self.work)
        self.okButton = QPushButton('확인')
        self.okButton.clicked.connect(self.okButtonClicked)

        self.cancelButton = QPushButton('알람 종료')
        self.cancelButton.clicked.connect(self.cancelButtonClicked)

        layout = QGridLayout()
        layout.addWidget(messageLabel, 0, 0)
        layout.addWidget(self.okButton, 1, 0)
        layout.addWidget(self.cancelButton, 1, 1)

        self.center()
        self.setLayout(layout)

    def okButtonClicked(self):
        self.close()

    def cancelButtonClicked(self):
        self.stopFlag = True
        self.close()

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())


class startAlarmDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.setupUI()
        self.stopFlag = False

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('알림 시작')
        self.setWindowIcon(QIcon('image/alarm.png'))

        messageLabel = QLabel('알림시작합니다.')
        self.okButton = QPushButton('확인')
        self.okButton.clicked.connect(self.okButtonClicked)

        layout = QGridLayout()
        layout.addWidget(messageLabel, 0, 0)
        layout.addWidget(self.okButton, 1, 0)

        self.center()
        self.setLayout(layout)

    def okButtonClicked(self):
        self.stopFlag = True
        self.close()

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())


class stopAlarmDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('알림 종료')
        self.setWindowIcon(QIcon('image/alarm.png'))

        messageLabel = QLabel('알림종료합니다.')
        self.okButton = QPushButton('확인')
        self.okButton.clicked.connect(self.okButtonClicked)

        layout = QGridLayout()
        layout.addWidget(messageLabel, 0, 0)
        layout.addWidget(self.okButton, 1, 0)

        self.center()
        self.setLayout(layout)

    def okButtonClicked(self):
        self.close()

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())
