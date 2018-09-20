f = open('sample2.txt','w')
data = """70
60
55
75
95
90
80
80
85
100"""
f.write(data)
with open('sample2.txt','r') as f:
    lines = f.readlines()
    sum = 0
    for line in lines :
        sum += int(line)
    average = sum / len(lines)
    with open('result2.txt','w') as f:
        data = 'sum = {}, average = {}~'.format(sum,average)
        f.write(data)
        f.close()