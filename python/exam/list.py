# 리스트 사용
aa = [0, 0, 0, 0]
hap = 0
aa[0] = 1
aa[1] = 2
aa[2] = 3
aa[3] = 4

hap = aa[0] + aa[1] + aa[2] + aa[3]
print(hap)

# append
aa = []
for i in range(0, 100):
    aa.append(0)
print(len(aa))

# 음수값으로 접근
aa = [10, 20, 30, 40]
print(aa[-1])
print(aa[-2])

# 콜론으로 범위 지정
aa = [10, 20, 30, 40]
print(aa[0:3])
print(aa[0:2])
print((aa[:2]))
aa = [10, 20, 30]
bb = [40, 50, 60]
print(aa + bb)
print(aa[::2])
print(aa[::-1])

# 리스트의 값 변경
aa = [10, 20, 30]
aa[1:2] = [200, 300]
print(aa)

# 리스트 값 삭제
del(aa[1])
print(aa)