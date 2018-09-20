image = '************************************************'

def star_make():
    upper = 1
    star = int(input('몇번 째 줄까지 만들것 입니까? '))
    for i in range(star):
        print(image[:upper])
        upper = upper +1
        if i == star:
            break

star_make()