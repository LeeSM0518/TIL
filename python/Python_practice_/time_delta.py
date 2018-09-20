import datetime

hundred = datetime.datetime.now() + datetime.timedelta(days=100)

print(hundred)

print(type(datetime.datetime.now()))

hundred_before = datetime.datetime.now() - datetime.timedelta(days=-100)

print(hundred_before)

tomorrow = datetime.datetime.now().replace(hour=9, minute=0, second=0) + datetime.timedelta(days=1)

print(tomorrow)