lst = [0 for _ in range(1001)]

lst[0] = 1

# 2, 3, 5
i2 = i3 = i5 = 0
next2, next3, next5 = 2, 3, 5

for l in range(1, 1001):
	lst[l] = min(next2, next3, next5)
	if lst[l] == next2:
		i2 += 1
		next2 = lst[i2] * 2
	if lst[l] == next3:
		i3 += 1
		next3 = lst[i3] * 3
	if lst[l] == next5:
		i5 += 1
		next5 = lst[i5] * 5

n = int(input())
print(lst[n - 1])