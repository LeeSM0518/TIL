# XML 문서 파싱하기
print('\nXML 문서 파싱하기')
from xml.etree.ElementTree import parse
tree = parse('note.xml')
note = tree.getroot()

# 애트리뷰트 값 읽기
print('\n애트리뷰트 값 읽기')
print('date = ', note.get('date'))
print(note.get('foo','default'))
print(note.keys())
print(note.items())

# XML 태그 접근하기
print('\nXML 태그 접근하기')
from_tag = note.find('from')
from_tags = note.findall('from')
from_text = note.findtext('from')

print(from_tag)
print(from_tags)
print(from_text)

childs = note.getiterator('from')
print(childs)
