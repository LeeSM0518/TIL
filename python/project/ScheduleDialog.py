from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QDialog, QLabel, QLineEdit, QPushButton, QGridLayout, QDesktopWidget


class ScheduleDialog(QDialog):
    def __init__(self):
        super().__init__()
        self.work = None
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('스케줄 입력')
        self.setWindowIcon(QIcon('image/note.png'))

        workLabel = QLabel('할일 : ')

        self.workEdit = QLineEdit()
        self.okButton = QPushButton('확인')
        self.cancelButton = QPushButton('취소')

        self.okButton.clicked.connect(self.okButtonClicked)
        self.cancelButton.clicked.connect(self.cancelButtonClicked)

        layout = QGridLayout()
        layout.addWidget(workLabel, 0, 0)
        layout.addWidget(self.workEdit, 0, 1)
        layout.addWidget(self.okButton, 1, 0)
        layout.addWidget(self.cancelButton, 1, 1)

        self.center()
        self.setLayout(layout)

    def okButtonClicked(self):
        self.work = self.workEdit.text()
        self.close()

    def cancelButtonClicked(self):
        self.work = 'cancel'
        self.close()

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())