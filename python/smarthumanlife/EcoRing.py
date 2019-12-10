import sys
import urllib.request

from PyQt5.QtWidgets import *
from bs4 import BeautifulSoup


class FineDust:
    url = "http://aqicn.org/city/daejeon/kr/"

    def __init__(self):
        self.getData()

    def getData(self):
        html = urllib.request.urlopen(self.url).read()
        soup = BeautifulSoup(html, 'html.parser')
        self.table = soup.find('div', {'class': 'aqivalue'})
        self.value = self.table.string
        self.title = self.table.get('title')
        self.color = self.table.get('style')


class App(QDialog):

    dust = FineDust()

    def __init__(self):
        super().__init__()
        self.setupUI()

    def setupUI(self):
        self.setGeometry(1000, 200, 300, 100)
        self.setWindowTitle('미세먼지 크롤링')

        valueLabel = QLabel(self.dust.value)
        valueLabel.setStyleSheet(self.dust.color)

        titleLabel = QLabel(self.dust.title)

        layout = QGridLayout()
        layout.addWidget(valueLabel, 0, 0)
        layout.addWidget(titleLabel, 0, 1)

        self.center()
        self.setLayout(layout)

    def center(self):
        qr = self.frameGeometry()
        cp = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = App()
    window.show()
    app.exec_()