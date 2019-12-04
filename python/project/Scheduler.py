import sys
import threading
import time

from PyQt5.QtGui import QIcon
from PyQt5.QtGui import QPixmap
from PyQt5.QtWidgets import QApplication, QWidget, QHBoxLayout, QVBoxLayout
from PyQt5.QtWidgets import QDialog, QLabel, QLineEdit, QPushButton, QGridLayout, QComboBox, QDesktopWidget


class Cycle:
    hour = 0
    minute = 0

    def __init__(self, h, m):
        self.hour = h
        self.minute = m

    def getHour(self):
        return self.hour

    def getMinute(self):
        return self.minute

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


class Schedule:
    work = ''
    cycle = Cycle(0, 0)

    def __init__(self, work, hour, minute):
        self.work = work
        self.cycle.hour = hour
        self.cycle.minute = minute

    def getWork(self):
        return self.work

    def setWork(self, work):
        self.work = work

    def getCycle(self):
        return self.cycle

    def setCycle(self, cycle):
        self.cycle = cycle


class PushAlarm(QDialog):

    def __init__(self, work):
        super().__init__()
        self.work = work
        self.stopFlag = False
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('알림 메시지')
        self.setWindowIcon(QIcon('image/alarm.gif'))

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
        self.setWindowIcon(QIcon('image/alarm.gif'))

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
        self.setWindowIcon(QIcon('image/alarm.gif'))

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


class Cycle:
    hour = 0
    minute = 0

    def __init__(self, h, m):
        self.hour = h
        self.minute = m

    def getHour(self):
        return self.hour

    def getMinute(self):
        return self.minute


class App(QWidget):
    workLayout = {}
    stopFlag = False

    def __init__(self):
        super().__init__()
        self.queue = []
        self.scheduler = Scheduler(self.queue)
        self.title = 'Python Project (LeeSangMin)'
        self.left = 10
        self.top = 10
        self.width = 640
        self.height = 480
        self.workCount = 0
        self.initUI()

    def initUI(self):
        self.setWindowTitle(self.title)
        self.setGeometry(self.left, self.top, self.width, self.height)

        self.mainLayout = QVBoxLayout()

        noteImageLabel = self.makeImageLabel('image/note.png', 50, 50)
        alarmImageLabel = self.makeImageLabel('image/alarm.gif', 50, 50)
        noneLabel1 = QLabel()
        self.createButton = QPushButton('일정 추가', self)
        self.createButton.clicked.connect(self.createButtonClicked)
        noneLabel2 = QLabel()

        self.startButton = QPushButton('알림 시작', self)
        self.startButton.clicked.connect(self.startButtonClicked)
        self.stopButton = QPushButton('알림 정지', self)
        self.stopButton.clicked.connect(self.stopButtonClicked)

        headerLayout = QHBoxLayout()
        headerLayout.addWidget(noteImageLabel)
        headerLayout.addWidget(self.createButton)
        headerLayout.addWidget(noneLabel2)

        headerLayout.addWidget(noneLabel1)
        headerLayout.addWidget(alarmImageLabel)
        headerLayout.addWidget(self.startButton)
        headerLayout.addWidget(self.stopButton)

        self.bodyLayout = QVBoxLayout()

        self.mainLayout.addLayout(headerLayout)
        self.mainLayout.addLayout(self.bodyLayout)
        self.setLayout(self.mainLayout)

        self.center()
        self.show()

    def startButtonClicked(self):
        self.stopFlag = False
        self.scheduler.startAlarm()
        dlg = startAlarmDialog()
        dlg.exec_()
        while True:
            if len(self.queue) != 0:
                work = self.queue.pop(0)
                dlg = PushAlarm(work)
                dlg.exec_()
                if dlg.stopFlag is True:
                    self.queue = []
                    return

    def stopButtonClicked(self):
        self.stopFlag = True
        self.queue = []
        dlg = stopAlarmDialog()
        dlg.exec_()
        self.scheduler.stopAlarm()

    def createButtonClicked(self):
        dlg = ScheduleDialog()
        dlg.exec_()
        work = dlg.work
        if work == 'cancel':
            return
        self.createScheduleLayout(work)

    def makeImageLabel(self, imagePath, w, h):
        label = QLabel(self)
        pixmap = QPixmap(imagePath)
        pixmap = pixmap.scaledToWidth(w)
        pixmap = pixmap.scaledToHeight(h)
        label.setPixmap(pixmap)
        return label

    def createScheduleLayout(self, work):
        if self.scheduler.existSchedule(work) != None:
            return
        self.scheduler.createSchedule(work, 0, 1)
        layout = QHBoxLayout()
        testLabel = QLabel()
        testLabel.setText(work)
        alarmSetButton = QPushButton('알림설정', self)

        schedule = self.scheduler.existSchedule(work)
        cycle = schedule.getCycle()

        alarmSetButton.clicked.connect(lambda: self.alarmSetButtonClicked(work, schedule))

        deleteButton = QPushButton('삭제', self)
        self.workLayout[work] = layout
        deleteButton.clicked.connect(lambda: self.deleteButtonClicked(work))

        layout.addWidget(testLabel)
        layout.addWidget(alarmSetButton)
        layout.addWidget(deleteButton)
        self.bodyLayout.addLayout(layout)

    def deleteButtonClicked(self, work):
        self.scheduler.deleteSchedule(work)
        layout = self.workLayout[work]
        while layout.count() > 0:
            layout.itemAt(0).widget().setParent(None)
        del self.workLayout[work]

    def alarmSetButtonClicked(self, work, schedule):
        dlg = AlarmDialog(schedule)
        dlg.exec_()
        schedule = self.scheduler.existSchedule(work)
        cycle = Cycle(dlg.hour, dlg.minute)
        schedule.setCycle(cycle)

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())


class Scheduler:
    scheduleList = []

    def __init__(self, queue):
        self.queue = queue
        self.alarmManager = AlarmManager(queue)

    def createSchedule(self, work, hour, minute):
        self.scheduleList.append(Schedule(work, hour, minute))

    def existSchedule(self, work):
        for sche in self.scheduleList:
            if sche.getWork() == work:
                return sche
        return None

    def deleteSchedule(self, work):
        remove = None
        for i in self.scheduleList:
            if i.getWork() == work:
                remove = i
                break
        self.scheduleList.remove(remove)

    def startAlarm(self):
        self.alarmManager.start(self.scheduleList)

    def stopAlarm(self):
        self.alarmManager.stop()

    def getScheduleList(self):
        return self.scheduleList

    def setScheduleList(self, sList):
        self.scheduleList = sList


class TimeThread:
    thread = None
    threadRunState = False
    stopFlag = False
    delayTime = 1

    def __init__(self, time, message, queue):
        self.queue = queue
        self.delayTime = time
        self.message = message

    def delayAndCall(self):
        while True:
            time.sleep(self.delayTime)
            if self.stopFlag is True:
                break
            self.queue.append(self.message)

    def startThread(self):
        if self.threadRunState is False:
            self.thread = threading.Thread(target=self.delayAndCall)
            self.stopFlag = False
            self.thread.start()
            self.threadRunState = True

    def setDelayTime(self, time):
        self.delayTime = time

    def stopThread(self):
        self.stopFlag = True
        self.threadRunState = False


class AlarmManager:
    threads = []

    def __init__(self, queue):
        self.queue = queue

    def start(self, scheduleList):
        for t in scheduleList:
            self.threads.append(self.createThread(t))
        for t in self.threads:
            t.startThread()

    def stop(self):
        for t in self.threads:
            t.stopThread()
        self.threads.clear()

    def createThread(self, schedule):
        cycle = schedule.getCycle()
        #delayTime = int(cycle.getHour()) * 60 * 60 + int(cycle.getMinute()) * 60
        delayTime = int(cycle.getMinute())
        thread = TimeThread(delayTime, schedule.getWork(), self.queue)
        return thread


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = App()
    sys.exit(app.exec_())
