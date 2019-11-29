import time
import threading


class Scheduler:
    scheduleList = []

    def addSchedule(self, work):
        if self.existSchedule(work):
            return False
        else:
            self.scheduleList.append(work)
            return True

    def deleteSchedule(self, work):
        if self.existSchedule(time):
            self.scheduleList.remove(work)
            return True
        else:
            return False

    def updateSchedule(self, beforeWork, newWork):
        if self.existSchedule(beforeWork):
            self.scheduleList.remove(beforeWork)
            self.scheduleList.append(newWork)
            return True
        else:
            return False

    def existSchedule(self, work):
        if work in self.scheduleList:
            return True
        else:
            return False


class DateTime:
    def getYearMonthDay(self):
        return self.getDateFromFormat('%Y-%m-%d')

    def getDay(self):
        return self.getDateFromFormat('%a')

    def getHour(self):
        return self.getDateFromFormat('%H')

    def getMinute(self):
        return self.getDateFromFormat('%M')

    def getDateFromFormat(self, code):
        return time.strftime(code, time.localtime(time.time()))


class TimeThread:
    thread = None
    threadRunState = False
    stopFlag = False
    delayTime = 1
    runMethod = None

    def delayAndCall(self):
        print('start')
        while True:
            if self.stopFlag is True:
                break
            time.sleep(self.delayTime)
            if self.runMethod is not None:
                self.runMethod()

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
