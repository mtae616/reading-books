	# 두개의 문자열 A, B
# A 편집 -> B
# insert, remove, replace
a = input()
b = input()

lst = [([0] * (len(b) + 1)) for _ in range(len(a) + 1)]

for i in range(1, len(a) + 1):
	lst[i][0] = i

for i in range(1, len(b) + 1):
	lst[0][i] = i

for i in range(1, len(a) + 1):
	for j in range(1, len(b) + 1):
		if a[i - 1] == b[j - 1]:
			lst[i][j] = lst[i - 1][j - 1]
		else:
			lst[i][j] = min(lst[i][j - 1], lst[i - 1][j - 1], lst[i - 1][j]) + 1

for i in range(len(a) + 1):
	print(lst[i])
# print(lst[-1][-1])
