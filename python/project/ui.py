import sys

from PyQt5.QtCore import QRect
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QHBoxLayout, QVBoxLayout, QGridLayout, QPushButton, QDialog, QLineEdit
from PyQt5.QtGui import QIcon, QPixmap

from project.ScheduleDialog import ScheduleDialog
from project.Scheduler import Scheduler

class App(QWidget):

    scheduler = Scheduler()


    def __init__(self):
        super().__init__()
        self.title = 'Python Project (LeeSangMin)'
        self.left = 10
        self.top = 10
        self.width = 640
        self.height = 480
        self.initUI()


    def initUI(self):
        self.setWindowTitle(self.title)
        self.setGeometry(self.left, self.top, self.width, self.height)

        self.mainLayout = QVBoxLayout()
        noteImageLabel = self.makeImageLabel('image/note.png', 50, 50)
        alarmImageLabel = self.makeImageLabel('image/alarm.png', 50, 50)
        noneLabel1 = QLabel()
        self.createButton = QPushButton('일정 추가', self)
        self.createButton.clicked.connect(self.createButtonClicked)
        self.deleteButton = QPushButton('일정 삭제', self)
        startButton = QPushButton('알림 시작', self)
        stopButton = QPushButton('알림 정지', self)

        headerLayout = QHBoxLayout()
        headerLayout.addWidget(noteImageLabel)
        headerLayout.addWidget(self.createButton)
        headerLayout.addWidget(self.deleteButton)

        headerLayout.addWidget(noneLabel1)
        headerLayout.addWidget(alarmImageLabel)
        headerLayout.addWidget(startButton)
        headerLayout.addWidget(stopButton)

        headerLayout.removeItem()

        self.mainLayout.addLayout(headerLayout)
        self.setLayout(self.mainLayout)

        self.show()

    def createButtonClicked(self):
        dlg = ScheduleDialog()
        dlg.exec_()
        work = dlg.work
        self.createScheduleLayout(work)

    def makeImageLabel(self, imagePath, w, h):
        label = QLabel(self)
        pixmap = QPixmap(imagePath)
        pixmap = pixmap.scaledToWidth(w)
        pixmap = pixmap.scaledToHeight(h)
        label.setPixmap(pixmap)
        return label

    def createScheduleLayout(self, work):
        self.scheduler.createSchedule(work, 0, 1)
        hLayout = QHBoxLayout()
        hLayout.addWidget(work)
        hLayout.addWidget(QLabel)
        # TODO 일정 + 알람설정 + 삭제버튼 들이 추가된
        #  레이아웃을 일정을 생성할 때마다 추가해준다.
        # hLayout.

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())