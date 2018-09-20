students = ['태연', '진우', '정현', '하늘', '성진']

for number, name in enumerate(students):
    print('{}번의 이름은 {}입니다.'.format(number, name))

students_dict= {'{}번'.format(number+1) : name for number ,name in enumerate(students)}
print()
print(students_dict)

scores = [85 , 92, 78, 90, 100]
print()
for x,y in zip(students, scores):
    print(x,y)

score_dic = { student : score for student, score in zip(students, scores)}
print()
print(score_dic)