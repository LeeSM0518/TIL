# XML 처리
# Q1
print('\nXML처리\nQ1')

from xml.etree.ElementTree import ElementTree, Element, SubElement, dump, parse

blog = Element('blog')
blog.attrib['date'] = '20151231'

SubElement(blog, 'subject').text = 'Why python?'
SubElement(blog, 'author').text = 'Eric'
SubElement(blog, 'content').text = 'You need Python!'

def indent(elem, level=0):
    i = '\n' + level*" "
    if len(elem):
        if not elem.text or not elem.text.strip():
            elem.text = i + " "
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
        for elem in elem:
            indent(elem, level + 1)
        if not elem.tail or not elem.tail.strip():
            elem.tail = i
    else:
        if level and (not elem.tail or not elem.tail.strip()):
            elem.tail = i

indent(blog)
dump(blog)

ElementTree(blog).write('blog.xml')
