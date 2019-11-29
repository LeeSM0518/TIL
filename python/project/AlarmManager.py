from .Schedule import Schedule
from .Cycle import Cycle
import time
import threading


class TimeThread:
    thread = None
    threadRunState = False
    stopFlag = False
    delayTime = 1
    runMethod = None

    def __init__(self, time):
        self.delayTime = time

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


class AlarmManager:
    threads = []

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
        delayTime = cycle.getHour() * 60 * 60 + cycle.getMinute() * 60
        thread = TimeThread(delayTime)
        return thread
