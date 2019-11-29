from .AlarmManager import AlarmManager
from .Schedule import Schedule


class Scheduler:
    scheduleList = []
    alarmManager = AlarmManager()

    def createSchedule(self, work, hour, minute):
        self.scheduleList.append(Schedule(work, hour, minute))

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
