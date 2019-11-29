from urllib.request import urlopen
from bs4 import BeautifulSoup
import ssl

context = ssl._create_unverified_context()
result = urlopen("https://www.hanbat.ac.kr/html/kr/cyber/cyber_02_01.html", context=context)
bsObj = BeautifulSoup(result.read(), "html.parser")

for tag in bsObj.findAll("tbody"):
    print(tag)