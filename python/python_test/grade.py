def grade_check(num):
    if 90<= num <=100:
        print('A')
    elif 80<= num <= 89:
        print('B')
    elif 70<= num <= 79:
        print('C')
    elif 60<= num <= 69:
        print('D')
    else:
        print('F')

student = int(input('성적을 입력하시오: '))
grade_check(student)