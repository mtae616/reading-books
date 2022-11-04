n = int(input())
lst = []
for _ in range(n):
	lst.append(list(map(int, input().split())))
for i in range(1, n):
	for j in range(len(lst[i])):
		l, r = 0, 0
		if i != j:
			l = lst[i - 1][j]
		if j > 0:
			r = lst[i - 1][j - 1]
		lst[i][j] += max(l, r)
print(max(lst[n - 1]))