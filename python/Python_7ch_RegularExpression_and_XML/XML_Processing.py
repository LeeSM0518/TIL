# XML 문서 생성하기
print('XML 문서 생성하기')
from xml.etree.ElementTree import Element, dump

note = Element('note')
to = Element('to')
to.text = "Tove"

note.append(to)
dump(note)

# SubElement 서브엘리먼트
print('\nSubElement 서브엘리먼트')
from xml.etree.ElementTree import Element, SubElement,dump

note = Element('note')
to = Element('to')
to.text = 'Tove'

note.append(to)
SubElement(note, 'from').text = 'Jani'

dump(note)

dummy = Element('dummy')
note.insert(2, dummy)
dump(note)
note.remove(dummy)

# 애트리뷰트 추가하기
print('\n애트리뷰트 추가하기')

from xml.etree.ElementTree import Element,SubElement,dump

note = Element('note')
to = Element('to')
to.text = 'Tove'

note.append(to)
SubElement(note,'from').text = 'Jani'
note.attrib['date'] = '20120104'

dump(note)

# XML 태그와 애트리뷰트를 추가한 완성된 소스
print('\nXML 태그와 애트리뷰트를 추가한 완성된 소스')
from xml.etree.ElementTree import Element, SubElement, dump

note = Element('note')
note.attrib['note'] = '20140104'

to = Element('to')
to.text = 'Jani'
note.append(to)

SubElement(note, 'from').text = 'Jani'
SubElement(note, 'heading').text = 'Reminder'
SubElement(note, 'body').text = "Don't forget me this weekend!"
dump(note)

# indent 함수
print('\nindent 함수')

from xml.etree.ElementTree import Element, SubElement, dump

note = Element('note')
note.attrib['date'] = '20180924'

to = Element('to')
to.text = 'Tove'
note.append(to)

SubElement(note, 'from').text = 'Jani'
SubElement(note, 'heading').text = 'Reminder'
SubElement(note, 'body').text = "Don't forget me this weekend!"

def indent(elem, level=0):
    i = '\n' + level*' '
    if len(elem):
        if not elem.text or not elem.text.strip():
            elem.text = i + " "
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
        for elem in elem:
            indent(elem, level+1)
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
    else:
        if level and (not elem.tail or not elem.tail.strip()):
            elem.tail = i

indent(note)
dump(note)

# 파일에 쓰기(write) 수행하기
print('\n파일에 쓰기(write) 수행하기')
from xml.etree.ElementTree import ElementTree
ElementTree(note).write('note.xml')
