for tc in range(int(input())):
	n, m = map(int, input().split())
	origin = list(map(int, input().split()))
	lst = [[0] * (m) for _ in range(n)]
	k = 0
	for i in range(n):
		for j in range(m):
			lst[i][j] = origin[k]
			k += 1
	for j in range(m):
		for i in range(n):
			l_u = 0
			l = 0
			l_d = 0
			if 0 <= i - 1 and 0 <= j - 1:
				l_u = lst[i - 1][j - 1]
			if 0 <= j - 1:
				l = lst[i][j - 1]
			if i + 1 < n and 0 <= j - 1:
				l_d = lst[i + 1][j - 1]
			lst[i][j] += max(l_u, l, l_d)
	print(lst)
