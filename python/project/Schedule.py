from .Cycle import Cycle


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