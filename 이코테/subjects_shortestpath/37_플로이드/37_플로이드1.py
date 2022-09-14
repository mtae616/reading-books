# 1 <= n <= 100 도시
# 1 <= m <= 100000 버스
# 시작 a 도착 b 한 번 타는 비용 c
n = int(input())
m = int(input())

INF = int(1e9)

lst = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

for i in range(n + 1):
	for j in range(n + 1):
		if i == j:
			lst[i][j] = 0
		else:
			lst[i][j] = INF

for i in range(m):
	a, b, c = map(int, input().split())
	lst[a][b] = min(lst[a][b], c)

for k in range(n + 1):
	for i in range(n + 1):
		for j in range(n + 1):
			if i != j:
				lst[i][j] = min(lst[i][k] + lst[k][j], lst[i][j])

for i in range(1, n + 1):
	for j in range(1, n + 1):
		if lst[i][j] == INF:
			print(0,end=' ')
		else:
			print(lst[i][j], end=' ')
	print()