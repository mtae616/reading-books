n = int(input())
m = int(input())

INF = int(1e9)

lst = [[INF for _ in range(n + 1)] for _ in range(n + 1)]

for i in range(n + 1):
	for j in range(n + 1):
		if i == j:
			lst[i][j] = 0

for _ in range(m):
	a, b, c = map(int, input().split())
	if lst[a][b] and lst[a][b] > c:
		lst[a][b] = c

for k in range(1, n + 1):
	for i in range(1, n + 1):
		for j in range(1, n + 1):
			lst[i][j] = min(lst[i][j], lst[i][k] + lst[k][j])

for i in range(1, n + 1):
	for j in range(1, n + 1):
		if lst[i][j] != INF:
			print(lst[i][j], end='\t')
		else:
			print(0, end='\t')
	print()
