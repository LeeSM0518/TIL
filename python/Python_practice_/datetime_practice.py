import datetime

def days_until_christmas():
    christmas_2030 = datetime.datetime(2030, 12, 25)
    days = (christmas_2030 - datetime.datetime.now()).days
    return days

print('{}일'.format(days_until_christmas()))