import random
import serial
import RPi.GPIO as GPIO
import time
from gpiozero import Button


GPIO.setmode(GPIO.BOARD)
averageCalculateButton = Button(21)
SERIALPORT = "/dev/ttyAMA0"
BAUDRATE = 115200
ser = serial.Serial(SERIALPORT, BAUDRATE)
ser.timeout = 0.1


class ToFCamera:
    averageDistance = 0.0
    DISTANCE_MAX = 0.0
    DISTANCE_MIN = 0
    TIME_MIN = 0
    TIME_MAX = 4

    def __init__(self):
        self.averageDistance = self.readDataToFile()
        self.DISTANCE_MAX = self.averageDistance * 2

    @staticmethod
    def saveDataToFile(data):
        f = open('saveAverageDistance.txt', 'w')
        f.write(str(data))
        f.close()

    @staticmethod
    def readDataToFile():
        f = open('saveAverageDistance.txt', 'r')
        data = f.readline()
        return float(data)

    def setAverageDistance(self):
        if averageCalculateButton.is_pressed:
            self.averageDistance = self.calculateAverageDistance()
            self.DISTANCE_MAX = self.averageDistance * 2
            self.saveDataToFile(self.averageDistance)

    @staticmethod
    def getDistance():
        distance = -1
        if ser.isOpen():
            try:
                ser.flushInput()
                ser.flushOutput()
                vals = [10, 34, 0, 0, 0, 0, 0, 0, 174, 87]
                command = bytes(vals)
                ser.write(command)
                valsResponse = ser.read(15)
                if len(valsResponse) is 15:
                    distance = (valsResponse[5] * 256 +
                                valsResponse[6]) / float(10.0)
                    time.sleep(0.1)
            except Exception as e:
                print('Error Communicating..: ' + str(e))
                ser.close()
            finally:
                pass
        else:
            print('Cannot open serial port')
        return distance

    def calculateAverageDistance(self):
        count = 0
        sum = 0.0

        start = time.time()
        while True:
            countTime = time.time() - start
            if countTime > 5:
                print('end')
                break
            distance = self.getDistance()
            if distance > 0:
                sum += distance
                count += 1
        avg = sum / count
        return avg


    def convertDistanceToTime(self, distance):
        return distance / (self.DISTANCE_MAX / self.TIME_MAX)


tof = ToFCamera()

while True:
    tof.setAverageDistance()
    distance = tof.getDistance()
    print('distance: ', distance)
    print('convert: ', tof.convertDistanceToTime(distance))
    time.sleep(0.001)
