scores = []
for i in range(5):
    score = int(input('성적을 입력하시오: '))
    scores.append(score)

sum = 0.0
for i in scores:
    sum += i
avg = sum / len(scores)
print(avg)

count = 0
for i in scores:
    if i >= 80:
        count += 1
print('80점 이상 성적을 받은 학생은 %d명 입니다.' % count)
