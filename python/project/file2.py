outFp = None
outStr = ''

outFp = open('data2.txt', 'w')

while True:
    outStr = input('내용 입력: ')
    if outStr != '':
        outFp.writelines(outStr + '\n')
    else:
        break

outFp.close()
print('종료')