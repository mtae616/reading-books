n = int(input())

INF = int(1e9)

lst = [[INF] * (n + 1) for _ in range(n + 1)]

for tc in range(int(input())):
	a, b, cost = map(int, input().split())
	if lst[a][b] != INF:
		lst[a][b] = min(lst[a][b], cost)
	else:
		lst[a][b] = cost

for i in range(1, n + 1):
	for j in range(1, n + 1):
		if i == j:
			lst[i][j] = 0

for k in range(1, n + 1):
	for i in range(1, n + 1):
		for j in range(1, n + 1):
			lst[i][j] = min(lst[i][j], lst[i][k] + lst[k][j])

for i in range(1, n + 1):
	for j in range(1, n + 1):
		if lst[i][j] == INF:
			print(0, end=' ')
		else:
			print(lst[i][j], end=' ')
	print()