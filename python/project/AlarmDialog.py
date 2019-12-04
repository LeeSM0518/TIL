from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QDialog, QLabel, QLineEdit, QPushButton, QGridLayout, QComboBox, QDesktopWidget


class AlarmDialog(QDialog):
    hourList = [str(i) for i in range(25)]
    minuteList = [str(i) for i in range(61)]
    hour = 0
    minute = 0
    originHour = 0
    originMinute = 0

    def __init__(self, schedule):
        super().__init__()
        cycle = schedule.getCycle()
        self.originHour = cycle.getHour()
        self.originMinute = cycle.getMinute()
        print('alarmDialog: ' + str(cycle.getHour()) + '시 ' + str(cycle.getMinute()) + '분 ')
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('알림 설정')
        self.setWindowIcon(QIcon('image/alarm.gif'))

        alarmLabel = QLabel('알림주기 : ')
        self.hourBox = QComboBox(self)
        self.hourBox.addItems(self.hourList)
        self.hourBox.currentIndexChanged.connect(self.hourBoxFunction)

        hourLabel = QLabel('시')

        self.minuteBox = QComboBox(self)
        self.minuteBox.addItems(self.minuteList)
        self.minuteBox.currentIndexChanged.connect(self.minuteBoxFunction)

        minuteLabel = QLabel('분')

        self.okButton = QPushButton('확인')
        self.cancelButton = QPushButton('취소')

        self.okButton.clicked.connect(self.okButtonClicked)
        self.cancelButton.clicked.connect(self.cancelButtonClicked)

        cycleText = '현재 주기: (' + str(self.originHour) + '시 ' + str(self.originMinute) + '분)'
        originCycleLabel = QLabel(cycleText)

        layout = QGridLayout()
        layout.addWidget(alarmLabel, 0, 0)
        layout.addWidget(self.hourBox, 0, 1)
        layout.addWidget(hourLabel, 0, 2)
        layout.addWidget(self.minuteBox, 0, 3)
        layout.addWidget(minuteLabel, 0, 4)
        layout.addWidget(originCycleLabel, 0, 5)
        layout.addWidget(self.okButton, 1, 0)
        layout.addWidget(self.cancelButton, 1, 1)

        self.center()
        self.setLayout(layout)

    def okButtonClicked(self):
        self.close()

    def cancelButtonClicked(self):
        self.hour = self.originHour
        self.minute = self.originMinute
        self.close()

    def hourBoxFunction(self):
        self.hour = self.hourBox.currentText()

    def minuteBoxFunction(self):
        self.minute = self.minuteBox.currentText()

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())
