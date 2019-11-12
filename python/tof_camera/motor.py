import RPi.GPIO as GPIO
import time
import threading


class VibrateMotor:
    motor = 0
    speed = 0
    thread = None
    stopFlag = False
    threadState = False

    def setPinNumber(self, pinNumber):
        self.motor = pinNumber
        GPIO.setmode(GPIO.BCM)
        GPIO.setwarnings(False)
        GPIO.setup(self.motor, GPIO.OUT)
        GPIO.output(self.motor, GPIO.LOW)

    def vibrate(self):
        GPIO.output(self.motor, GPIO.HIGH)
        time.sleep(0.15)
        GPIO.output(self.motor, GPIO.LOW)
        time.sleep(0.15)

    def stopVibrate(self):
        self.stopFlag = True
        GPIO.output(self.motor, GPIO.LOW)
        print("2")

    def setSpeed(self, sec):
        self.speed = float(sec)

    def startVibrate(self):
        self.stopFlag = False
        if self.threadState is True:
            return
        threadState = True
        while True:
            if self.stopFlag is True:
                self.threadState = False
                break
            self.vibrate()
            time.sleep(self.speed)
            self.vibrate()
            time.sleep(self.speed)

    def startThreadVibrate(self):
        thread = threading.Thread(target=self.startVibrate)
        thread.start()


x = VibrateMotor()
x.setPinNumber(23)

while (True):
    a = input('value: ')
    if a == 'stop':
        x.stopVibrate()
    elif a == 'start':
        x.startThreadVibrate()
    else:
        x.setSpeed(a)


